package com.example.myapplication;

import android.app.Activity;
import android.text.method.MetaKeyKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements Filterable
{

    private ArrayList<MainData> dataArrayList;
    private ArrayList<MainData> dataackup;
    private Activity activity;

    public MainAdapter(Activity activity,ArrayList<MainData> dataArrayList,ArrayList<MainData> dataackup)
    {
        this.activity = activity;
        this.dataArrayList = dataArrayList;
        this.dataackup = dataackup;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MainData data = dataArrayList.get(position);
        Glide.with(activity).load(data.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.textView.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagerecycler);
            textView = itemView.findViewById(R.id.textrecycler);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<MainData> filterdata = new ArrayList<>();
            if (constraint.toString().isEmpty())
                filterdata.addAll(dataackup);
            else
            {
                for (MainData oj : dataackup)
                {
                    if (oj.getName().toString().toLowerCase().contains(constraint.toString().toLowerCase()))
                        filterdata.add(oj);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            dataArrayList.clear();
            dataArrayList.addAll((ArrayList<MainData>)results.values);
            notifyDataSetChanged();
        }
    };
}
