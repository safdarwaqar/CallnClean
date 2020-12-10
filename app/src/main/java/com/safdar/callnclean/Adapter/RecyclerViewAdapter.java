package com.safdar.callnclean.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.safdar.callnclean.Data.PackageData;
import com.safdar.callnclean.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder> {
    ArrayList<PackageData> arrayList;
    Context context;

    public RecyclerViewAdapter(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        String a=(arrayList.get(position).getItemName());
        holder.itemName.setText(a);
        holder.itemDesc.setText(arrayList.get(position).getItemDesc());



        holder.itemAddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,a,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemDesc;
        Button itemAddToCartButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imageView_item);
            itemName = itemView.findViewById(R.id.tv_order_name_itemlayout);
            itemDesc = itemView.findViewById(R.id.tv_order_desc_itemlayout);
            itemAddToCartButton = itemView.findViewById(R.id.bt_order_cart);

        }
    }
}
