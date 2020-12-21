package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    ArrayList<CartModal> lstcart ;
    private RecyclerView myrv;
    private CartAdapter cartAdapter;
    Button buttonwish;
    CardView cardView,cardView1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cardView1 = findViewById(R.id.filterCard);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this,FilterActivity.class);
                startActivity(i);
            }
        });
        cardView = findViewById(R.id.sortcard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetModal bottomSheetModal = new BottomSheetModal();
                bottomSheetModal.show(getSupportFragmentManager(),"App");
            }
        });
        myrv = (RecyclerView) findViewById(R.id.recyclerview);
        buttonwish = (Button) findViewById(R.id.wishbutton);
        buttonwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,WishListActivity.class));

            }
        });
        try {
            loadJSONFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void loadJSONFromAsset() throws IOException, JSONException {
        String json = null;
        InputStream is = getAssets().open("cart.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        JSONObject obj = new JSONObject(json);


        lstcart = new ArrayList<>();
        JSONArray m_jArry = obj.getJSONArray("cart");
        HashMap<String, String> m_li;

        for (int i = 0; i < m_jArry.length(); i++) {
            CartModal cat = new CartModal();
            JSONObject jo_inside = m_jArry.getJSONObject(i);
            Log.d("Details-->", jo_inside.getString("name"));
            Log.d("Details-->", jo_inside.getString("college"));

//            String getTxt_no_table = jo_inside.getString("name");
//            String getTxt_pax = jo_inside.getString("college");

            cat.setId(jo_inside.getInt("id"));
            cat.setName(jo_inside.getString("name"));
            cat.setSubject(jo_inside.getString("subject"));
            cat.setPercentage(jo_inside.getString("percentage"));
            cat.setCollege(jo_inside.getString("college"));
//            String name=jo_inside.getString("name");
//            String college=jo_inside.getString("college");
//            m_li = new HashMap<String, String>();
//            m_li.put("name", name);
//            m_li.put("college", college);
            lstcart.add(cat);
        }

        Recycler();
//        myrv.setAdapter(cartAdapter);
//        myrv.setHasFixedSize(true);
//        myrv.setLayoutManager(new GridLayoutManager(this, 6));
//        myrv.setLayoutManager(new LinearLayoutManager(this));
    }
    private void Recycler(){

        cartAdapter = new CartAdapter(this,lstcart);
        myrv.setAdapter(cartAdapter);
        myrv.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        myrv.setLayoutManager(staggeredGridVertical);
    }
}
