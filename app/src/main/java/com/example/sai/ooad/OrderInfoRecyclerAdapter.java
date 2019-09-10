package com.example.sai.ooad;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderInfoRecyclerAdapter extends RecyclerView.Adapter<OrderInfoRecyclerAdapter.Dataholder> {

    List<MenuItem> menuItemList;
    Context context;

    public OrderInfoRecyclerAdapter(List<MenuItem> list, Context c) {
        menuItemList = list;
        context = c;
    }


    @NonNull
    @Override
    public Dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customer_cart_recycler_layout,null);

        return new Dataholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dataholder holder, int position) {
        holder.itemName.setText(menuItemList.get(position).name);
        holder.itemPrice.setText(String.valueOf(menuItemList.get(position).price));
        Log.i("orderinfo_name",menuItemList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public class Dataholder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        public Dataholder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.customer_cart_item_name);
            itemPrice = itemView.findViewById(R.id.customer_cart_item_price);
        }
    }
}
