package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {

//    private Context mCtx;
//    private ArrayList<Page> pagedata;
//
//    public PageAdapter(Context mCtx, ArrayList<Page> pagedata) {
//        this.mCtx = mCtx;
//        this.pagedata = pagedata;
//    }
//
//    @NonNull
//    @Override
//    public PageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new PageAdapter.MyViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_cat, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PageAdapter.MyViewHolder holder, int position) {
//
//        holder.item_id.setText(""+pagedata.get(position).getItem_id());
//        holder.item_name.setText(""+pagedata.get(position).getItem_proname());
//        holder.item_price.setText(""+pagedata.get(position).getItem_proprice());
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return pagedata.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder
//    {
//        TextView item_id,item_name,item_price;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            item_id = (TextView) itemView.findViewById(R.id.item_id1);
//            item_name = (TextView) itemView.findViewById(R.id.item_productname1);
//            item_price = (TextView) itemView.findViewById(R.id.item_productprice1);
//        }
//    }

    private ArrayList<Page> userModalArrayList;
    private Context context;

    // creating a constructor.
    public PageAdapter(ArrayList<Page> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PageAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.MyViewHolder holder, int position) {

        holder.item_id.setText(""+userModalArrayList.get(position).getFirst_name());
        holder.item_name.setText(""+userModalArrayList.get(position).getLast_name());
        holder.item_price.setText(""+userModalArrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userModalArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_id,item_name,item_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = (TextView) itemView.findViewById(R.id.idTVFirstName);
            item_name = (TextView) itemView.findViewById(R.id.idTVLastName);
            item_price = (TextView) itemView.findViewById(R.id.idTVEmail);
        }
    }
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        // creating a variable for our text view and image view.
//        private TextView firstNameTV, lastNameTV, emailTV;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            // initializing our variables.
//            firstNameTV = itemView.findViewById(R.id.idTVFirstName);
//            lastNameTV = itemView.findViewById(R.id.idTVLastName);
//            emailTV = itemView.findViewById(R.id.idTVEmail);
//            userIV = itemView.findViewById(R.id.idIVUser);
//        }
//    }
}
