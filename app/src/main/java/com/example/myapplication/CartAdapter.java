package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CartModal> items;

    public CartAdapter(Context context, ArrayList<CartModal> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyViewHolder holder, int position) {

        final CartModal ld=items.get(position);
        int id=ld.getId();
        holder.name.setText(items.get(position).getName());
        holder.subject.setText(items.get(position).getSubject());
        holder.percentage.setText(items.get(position).getPercentage());
        holder.college.setText(items.get(position).getCollege());
        //holder.itemOffer.setText(items.get(position).getProductoffer());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent cab=new Intent(context,ProductDecActivity.class);
//                cab.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                cab.putExtra("item_id",items.get(position).getItem_id());
//                context.startActivity(cab);
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.button.setText("Remove from WishList");
                Toast.makeText(context, "Added to WishList", Toast.LENGTH_SHORT).show();
                WishlistModal wishlist=new WishlistModal();
                int id=ld.getId();
                String name=ld.getName();
                String college=ld.getCollege();

                wishlist.setId(id);
                wishlist.setName(name);
                wishlist.setCollege(college);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,subject, percentage, college;
        CardView cardView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            subject = (TextView) itemView.findViewById(R.id.subject);
            percentage = (TextView) itemView.findViewById(R.id.percetage);
            college = (TextView) itemView.findViewById(R.id.college);
            button = (Button) itemView.findViewById(R.id.wish);
            cardView=(CardView) itemView.findViewById(R.id.cardview);

        }
    }
}
