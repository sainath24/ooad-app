package com.example.sai.ooad;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerMerchantListRecyclerAdapter extends RecyclerView.Adapter<CustomerMerchantListRecyclerAdapter.DataHolder> {

    List<String> merchantList;
    String merchant;
    Context context;

    public CustomerMerchantListRecyclerAdapter(List<String>merchantList, Context c) {
        this.merchantList = merchantList;
        context = c;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customer_merchant_list_recycler_layout,null);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, final int position) {
        merchant = merchantList.get(position);

        holder.merchantName.setText(merchant);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerHome.selectedMerchant = merchantList.get(position);
                CustomerHome.loadFragment();
            }
        });

    }

    @Override
    public int getItemCount() {
        return merchantList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        TextView merchantName;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            merchantName = itemView.findViewById(R.id.merchant_name);
        }
    }
}
