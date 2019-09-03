package com.example.sai.ooad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MerchantMenuRecyclerViewAdapter extends RecyclerView.Adapter<MerchantMenuRecyclerViewAdapter.DataHolder> {

    List<MenuItem> menuList;
    Context context;
    MenuItem menuItem;

    public MerchantMenuRecyclerViewAdapter(List<MenuItem> menu, Context c) {
        menuList = menu;
        context = c;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_layout,null);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, final int position) {

        menuItem = menuList.get(position);
        holder.name.setText(menuItem.name);
        holder.price.setText("Rs. " + menuItem.price);
        if(menuItem.isAvailable) {
            holder.available.setTextColor(context.getResources().getColor(R.color.available));
            holder.available.setText("AVAILABLE");
        }
        else {
            holder.available.setTextColor(context.getResources().getColor(R.color.unavailable));
            holder.available.setText("UNAVAILABLE");
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Menu/testing123");
                databaseReference.child(menuItem.name).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        menuList.remove(position);
                        MerchantMenuFragment.merchantMenuRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MerchantAddItem.class);
                intent.putExtra("item_name",menuItem.name);
                intent.putExtra("item_price",menuItem.price);
                intent.putExtra("item_quantity",menuItem.quantity);
                intent.putExtra("item_availability",menuItem.isAvailable);
                intent.putExtra("item_position",position);
                intent.putExtra("flag",100);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        TextView name,price,available;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            available = itemView.findViewById(R.id.item_availability);


        }
    }

}
