package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Fragment fragment = null;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Switch simple_switch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        frameLayout = (FrameLayout) findViewById(R.id.container);
        fragment = new HomeMainFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();


        simple_switch = (Switch) findViewById(R.id.simple_switch);
        simple_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String statusSwitch1;
                if (simple_switch.isChecked()) {
                    fragment = new MyAccountFragment();                }
                else {
                    fragment = new HomeFragment();
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                switch (tab.getPosition()) {
//                    case 0:
//                        fragment = new HomeFragment();
//                        break;
//                    case 1:
//                        fragment = new MyAccountFragment();
//                        break;
//                }
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeMainFragment();
                        break;
                    case R.id.nav_shop:
                        fragment = new MyAccountFragment();
                        break;
//                            case R.id.navigation_notifications:
//                                fragment = new HomeFragment();
//                                return true;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                return true;
            }
        });
    }
}
