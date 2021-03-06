package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class PagenationDataActivity extends AppCompatActivity {

    ArrayList<PagenationData> lstpagedata ;
    private RecyclerView recyclerView;
    private PagenationDataAdapter pagenationDataAdapter;
    String category = "http://13.232.113.112/nanocart_api/index.php/Api/nc_record_details";
    int page = 1,limit = 10;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagenation_data);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.item_recyclerview);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

//        nestedScrollView = findViewById(R.id.scrollview1);
        progressBar = findViewById(R.id.progressbar1);
//        loadPagenationData();
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())
//                {
//                    page++;
//                    progressBar.setVisibility(View.VISIBLE);
//                    loadPagenationData();
//                }
//            }
//        });
        loadPagenationData();


    }

    private void loadPagenationData()
    {
//        final String catid=Integer.toString(str);
        // final String userid=Integer.toString(user.getUser_id());

//        System.out.println(catid);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Api/nc_record_details",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null)
                        {
                            progressBar.setVisibility(View.GONE);
                            mShimmerViewContainer.stopShimmerAnimation();
                            mShimmerViewContainer.setVisibility(View.GONE);
                        }
                        Log.d("strrrrr", ">>" + response);
                        System.out.println(response);

                        try {
                            JSONObject obj = new JSONObject(response);

//                            if (obj.getInt("status") == 1) {
                                if(obj.getInt("status")==1)
                                {

                                lstpagedata = new ArrayList<>();
                                    JSONArray dataArray = obj.getJSONArray("user_data");
//                                JSONArray copyarray = dataArray;
                                for (int i = 0; i < dataArray.length(); i++) {
                                    Log.d("strrrrr", "inside for" );

                                    PagenationData pagenationData = new PagenationData();
//                                    if (i<=limit) {
                                    JSONObject objsub = dataArray.getJSONObject(i);
                                    pagenationData.setItem_id(objsub.getInt("item_id"));
                                    pagenationData.setItem_proname(objsub.getString("product_name"));
                                    pagenationData.setItem_proprice(objsub.getString("product_price"));
                                        lstpagedata.add(pagenationData);
//                                    }

//                                    lstpagedata.add(pagenationData);

                                }
                                    Log.d("strrrrr", "outside for" );


                                    Recycler();
                                    Log.d("strrrrr", "after recycler" );

                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", "8");
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");
                //               params.put("user_id",userid);
                return params;
            }
        };
        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void Recycler(){

        pagenationDataAdapter = new PagenationDataAdapter(this,lstpagedata);
        recyclerView.setAdapter(pagenationDataAdapter);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridVertical);
//        pagenationDataAdapter = new PagenationDataAdapter(this,lstpagedata);
//        pagenationDataAdapter.notifyDataSetChanged();
//        recyclerView.setAdapter(pagenationDataAdapter);
//        recyclerView.setHasFixedSize(true);
//          recyclerView.setLayoutManager(new GridLayoutManager(this,3));
////        StaggeredGridLayoutManager staggeredGridVertical=new
////                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
////        recyclerViewSub.setLayoutManager(staggeredGridVertical);

    }
}
