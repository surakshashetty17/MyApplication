package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class WishListActivity extends AppCompatActivity {

    ArrayList<WishlistModal> wishlists;
    RecyclerView rv;
    WishlistAdapter wishlistadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        rv=(RecyclerView)findViewById(R.id.recyclerview1);
//        try {
//            JSONObject obj = new JSONObject(readJSONFromAsset());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        try {
            loadJSONFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Recycler();

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


        wishlists = new ArrayList<>();
        JSONArray m_jArry = obj.getJSONArray("cart");
        HashMap<String, String> m_li;

        for (int i = 0; i < m_jArry.length(); i++) {
            WishlistModal cat = new WishlistModal();
            JSONObject jo_inside = m_jArry.getJSONObject(i);
            Log.d("Details-->", jo_inside.getString("name"));
            Log.d("Details-->", jo_inside.getString("college"));

//            String getTxt_no_table = jo_inside.getString("name");
//            String getTxt_pax = jo_inside.getString("college");

            cat.setId(jo_inside.getInt("id"));
            cat.setName(jo_inside.getString("name"));
            cat.setCollege(jo_inside.getString("college"));
//            String name=jo_inside.getString("name");
//            String college=jo_inside.getString("college");
//            m_li = new HashMap<String, String>();
//            m_li.put("name", name);
//            m_li.put("college", college);
            wishlists.add(cat);
        }

        Recycler();
//        myrv.setAdapter(cartAdapter);
//        myrv.setHasFixedSize(true);
//        myrv.setLayoutManager(new GridLayoutManager(this, 6));
//        myrv.setLayoutManager(new LinearLayoutManager(this));
    }
//    public String readJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getAssets().open("cart.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

    private void Recycler(){

        wishlistadapter = new WishlistAdapter(wishlists, this);
        rv.setAdapter(wishlistadapter);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridVertical);
    }
}
