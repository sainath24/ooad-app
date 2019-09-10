package com.example.sai.ooad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerCartRecyclerAdapter extends RecyclerView.Adapter<CustomerCartRecyclerAdapter.Dataholder> {
    List<MenuItem> orders;
    Context context;
    MenuItem orderItem;

    public CustomerCartRecyclerAdapter(List<MenuItem> order, Context c) {
        orders = order;
        context = c;
    }


    @NonNull
    @Override
    public Dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_cart_recycler_layout,null);
        return new Dataholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dataholder holder, final int position) {
        orderItem = orders.get(position);
        holder.itemName.setText(orderItem.name);
        holder.itemPrice.setText(String.valueOf(orderItem.price));


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CustomerHome.customerOrder.total = CustomerHome.customerOrder.total - orders.get(position).price;
                CustomerCart.total.setText("Total: " + CustomerHome.customerOrder.total);
                orders.remove(position);
                CustomerCart.customerCartRecyclerAdapter.notifyDataSetChanged();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Dataholder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;

        public Dataholder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.customer_cart_item_name);
            itemPrice = itemView.findViewById(R.id.customer_cart_item_price);
        }
    }
}
