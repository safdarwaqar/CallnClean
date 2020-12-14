package com.safdar.callnclean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.safdar.callnclean.Data.PackageData;
import com.safdar.callnclean.R;

import java.util.ArrayList;
import java.util.List;

public class CartRecyclerViewAdapter extends FirestoreRecyclerAdapter<PackageData, CartRecyclerViewAdapter.CartViewholder> {

    Context context;

    public CartRecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<PackageData> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartViewholder holder, int position, @NonNull PackageData model) {
        holder.itemName.setText(model.getItemName());
        holder.itemPrice.setText(model.getItemPrice());
        Glide.with(context).load(model.getItemImage()).into(holder.imageView);
    }

    @NonNull
    @Override
    public CartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewholder(view);
    }


    public class CartViewholder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        ImageView imageView;

        public CartViewholder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.tv_item_name_cart);
            imageView = itemView.findViewById(R.id.image_cart);
            itemPrice = itemView.findViewById(R.id.tv_item_price_cart);
        }
    }
}
