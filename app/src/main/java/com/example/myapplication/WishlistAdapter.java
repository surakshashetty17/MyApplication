package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{

    ArrayList<WishlistModal> wishlists;
    Context context;

    public WishlistAdapter(ArrayList<WishlistModal> wishlists, Context context) {
        this.wishlists = wishlists;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WishlistAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.wishlist_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final WishlistModal ld = wishlists.get(position);

//        Picasso.with(context)
//                .load(ld.getImageid())
//                .into(viewHolder.img);
//        holder.name.setText(wishlists.get(position).getName());
//        holder.college.setText(wishlists.get(position).getCollege());

        holder.name.setText(ld.getName());
        holder.college.setText(ld.getCollege());

    }

    @Override
    public int getItemCount() {
        return wishlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,college;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                name=(TextView)itemView.findViewById(R.id.name1);
                college=(TextView)itemView.findViewById(R.id.college1);

        }
    }
}
