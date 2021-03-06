package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PagenationDataAdapter extends RecyclerView.Adapter<PagenationDataAdapter.MyViewHolder>
{

    private Context mCtx;
    private ArrayList<PagenationData> pagedata;

    public PagenationDataAdapter(Context mCtx, ArrayList<PagenationData> pagedata) {
        this.mCtx = mCtx;
        this.pagedata = pagedata;
    }

    @NonNull
    @Override
    public PagenationDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new PagenationDataAdapter.MyViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_catalog, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PagenationDataAdapter.MyViewHolder holder, int position)
    {
        holder.item_id.setText(""+pagedata.get(position).getItem_id());
        holder.item_name.setText(""+pagedata.get(position).getItem_proname());
        holder.item_price.setText(""+pagedata.get(position).getItem_proprice());
    }

    @Override
    public int getItemCount()
    {
        return pagedata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_id,item_name,item_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = (TextView) itemView.findViewById(R.id.item_id);
            item_name = (TextView) itemView.findViewById(R.id.item_productname);
            item_price = (TextView) itemView.findViewById(R.id.item_productprice);
        }
    }
}
