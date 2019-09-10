package com.example.sai.ooad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MerchantPendingOrderRecyclerAdapter extends RecyclerView.Adapter<MerchantPendingOrderRecyclerAdapter.Dataholder> {

    List<Orders> ordersList;
    Context context;

    public MerchantPendingOrderRecyclerAdapter(List<Orders> orders,Context c) {
        ordersList = orders;
        context = c;
    }

    @NonNull
    @Override
    public Dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.merchant_pending_orders_recycler_layout,null);
        return new Dataholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dataholder holder, final int position) {
        holder.total.setText(String.valueOf(ordersList.get(position).total));
        holder.orderId.setText(ordersList.get(position).orderId);
        holder.name.setText(ordersList.get(position).customerName);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "Order done!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,OrderInfo.class);
                intent.putExtra("order",ordersList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class Dataholder extends RecyclerView.ViewHolder {

        TextView orderId,name,total;

        public Dataholder(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.merchant_pending_orders_recycler_order_id);
            name = itemView.findViewById(R.id.merchant_pending_orders_recycler_customer_name);
            total = itemView.findViewById(R.id.merchant_pending_orders_recycler_total);
        }
    }
}
