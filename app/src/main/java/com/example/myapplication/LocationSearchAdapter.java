package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationSearchAdapter extends RecyclerView.Adapter<LocationSearchAdapter.MyViewHolder>
{

    private Context mCtx;
    private ArrayList<LocationSearch> locdata;

    public LocationSearchAdapter(Context mCtx, ArrayList<LocationSearch> locdata) {
        this.mCtx = mCtx;
        this.locdata = locdata;
    }
    @NonNull
    @Override
    public LocationSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationSearchAdapter.MyViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_search, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LocationSearchAdapter.MyViewHolder holder, final int position) {
        holder.item_pincode.setText(""+locdata.get(position).getPincode());
        holder.item_officename.setText(""+locdata.get(position).getOfficename());
        holder.item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                HomeMainFragment myFragment = new HomeMainFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_a, myFragment).addToBackStack(null).commit();

//                Fragment fragment = new HomeMainFragment();
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_a, fragment);
//                ft.commit();
                Intent intent = new Intent(v.getContext(),HomeActivity.class);
//                intent.putExtra("index",1);  //Position means 0,1,2

                intent.putExtra("fragment_index_key", 1);
                intent.putExtra("pincode",locdata.get(position).getPincode());
                intent.putExtra("officename",locdata.get(position).getOfficename());
                v.getContext().startActivity(intent);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                HomeMainFragment myFragment = new HomeMainFragment();
//                //Create a bundle to pass data, add data, set the bundle to your fragment and:
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_a, myFragment).addToBackStack(null).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return locdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_pincode,item_officename;
        CardView item_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_pincode = (TextView) itemView.findViewById(R.id.item_pincode);
            item_officename = (TextView) itemView.findViewById(R.id.item_offincename);
            item_card = (CardView) itemView.findViewById(R.id.searchitem_cardview);

//            item_card.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    HomeMainFragment newFragment = new HomeMainFragment();
//                    FragmentTransaction transaction = ((FragmentActivity)mCtx).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_a, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
////
//                }
//            });

        }
    }
}
