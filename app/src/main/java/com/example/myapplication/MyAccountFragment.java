package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }

    LinearLayout linear_personal,linear_business,linear_banner,linear_currentlocation,linear_bank,linear_franchise,linear_order,linear_gallery,linear_catalog,linear_profile,linear_pincode,linear_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_account, container, false);
        View rootView = inflater.inflate(R.layout.fragment_my_account, container, false);


        linear_personal = (LinearLayout) rootView.findViewById(R.id.linear_personaldetailsf1);
        linear_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),PersonalDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_business = (LinearLayout) rootView.findViewById(R.id.linear_businessdetailsf1);
        linear_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),BusinessDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_banner = (LinearLayout) rootView.findViewById(R.id.linear_bannerf1);
        linear_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),SpinnerCheckboxActivity.class);
                startActivity(i);
            }
        });

        linear_currentlocation = (LinearLayout) rootView.findViewById(R.id.linear_updatelocationf1);
        linear_currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),MapSearchActivity.class);
                startActivity(i);
            }
        });

        linear_bank = (LinearLayout) rootView.findViewById(R.id.linear_bankdetailsf1);
        linear_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),BankDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_franchise = (LinearLayout) rootView.findViewById(R.id.linear_franchisepremiumf1);
        linear_franchise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),VendorPremiumActivity.class);
                startActivity(i);
            }
        });

        linear_order = (LinearLayout) rootView.findViewById(R.id.linear_ordersf1);
        linear_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),OrdersActivity.class);
                startActivity(i);
            }
        });

        linear_gallery = (LinearLayout) rootView.findViewById(R.id.linear_galleryf1);
        linear_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),GalleryImagesActivity.class);
                startActivity(i);
            }
        });

        linear_catalog = (LinearLayout) rootView.findViewById(R.id.linear_catalogf1);
        linear_catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),CatalogActivity.class);
                startActivity(i);
            }
        });

        linear_profile = (LinearLayout) rootView.findViewById(R.id.linear_profilef1);
        linear_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),ProfilePicActivity.class);
                startActivity(i);
            }
        });

        linear_pincode = (LinearLayout) rootView.findViewById(R.id.linear_pincodeenabledf1);
        linear_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),PincodeEnableActivity.class);
                startActivity(i);
            }
        });

        linear_password = (LinearLayout) rootView.findViewById(R.id.linear_changepasswordf1);
        linear_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        return rootView;


    }

}
