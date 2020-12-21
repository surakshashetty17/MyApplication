package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterAdapter  extends RecyclerView.Adapter<SearchFilterAdapter.ViewHolder> {

    private SearchFilterModal[] listdata;
//    private List<String> list1;
//    ArrayList<SearchFilterModal> listdata  = new ArrayList<SearchFilterModal>();
    ArrayList<SearchFilterModal> ackup;


    public SearchFilterAdapter(SearchFilterModal[] data) {
        this.listdata = data;
    }

    @NonNull
    @Override
    public SearchFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchFilterAdapter.ViewHolder holder, int position) {

        final SearchFilterModal myListData = listdata[position];
        holder.name.setText(listdata[position].getName());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textfilterame);
        }
    }
//    public void updateList(List<String> list)
//    {
//        list1 = new ArrayList<>();
//        list1.addAll(list);
//        notifyDataSetChanged();
//    }
}
