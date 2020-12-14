package com.safdar.callnclean.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.safdar.callnclean.CartActivity;
import com.safdar.callnclean.Data.PackageData;
import com.safdar.callnclean.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerViewAdapter extends FirestoreRecyclerAdapter<PackageData, RecyclerViewAdapter.OrderViewHolder> {


    Context context;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<PackageData> options, Context context) {
        super(options);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull PackageData model) {
        CollectionReference db = FirebaseFirestore.getInstance().collection("packages");

        holder.itemName.setText(model.getItemName());
        holder.itemDesc.setText(model.getItemDesc());
        holder.itemPrice.setText(model.getItemPrice());
        if (model.getItemImage() != null) {
            Glide.with(context).load(model.getItemImage()).into(holder.itemImage);
        }

        if (model.getAddedToCart()) {
            holder.itemAddToCartButton.setText("Added To Cart");
            holder.itemAddToCartButton.setEnabled(false);
        } else {
            holder.itemAddToCartButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    String documentId = getSnapshots().getSnapshot(position).getId();
                    Toast.makeText(context, documentId, Toast.LENGTH_SHORT).show();
                    PackageData packageData =
                            new PackageData(model.getItemName(), model.getItemDesc(), model.getItemPrice(), model.getItemImage(), true);
                    FirebaseFirestore.getInstance()
                            .collection("addToCart").add(packageData).addOnSuccessListener(documentReference -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("addedToCart", true);

                        db.document(documentId).update(map);

                        Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
                    });
                }
            });

        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(view);
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemDesc, itemPrice;
        Button itemAddToCartButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imageView_item);
            itemName = itemView.findViewById(R.id.tv_order_name_itemlayout);
            itemDesc = itemView.findViewById(R.id.tv_order_desc_itemlayout);
            itemPrice = itemView.findViewById(R.id.tv_order_price);
            itemAddToCartButton = itemView.findViewById(R.id.bt_order_cart);

        }
    }
}
