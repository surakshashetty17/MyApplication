package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
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
//    private int page = 0;
    String category = "http://13.232.113.112/nanocart_api/index.php/Api/nc_record_details";
    int page = 0,limit = 100;
    int currentItems, totalItems, scrollOutItems;
    NestedScrollView nestedScrollView;
    LinearLayoutManager manager;
    ProgressBar progressBar;
    private ShimmerFrameLayout mShimmerViewContainer;
    Boolean isScrolling = false;
//    private int page = 1;
    private int totalItemcount;
    private int firstvisibleItem;
    private int visibleItemcount;
    private int previoustotal;
    private boolean load = true;
    private boolean loading= true;
    private int pagenumber;
    private int visibleitemcount,totalitemcount,pastvisibleitems;
    private SwipeRefreshLayout swipeRefreshLayout;
    JSONArray dataArray;


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
        nestedScrollView = findViewById(R.id.nested_scrollview1);
//        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent,null));

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                lstpagedata.clear();
//                pagenumber = 1;
//                loading = true;
//                if(new CheckInter().checkInternet(PagenationDataActivity.this)) {
//                    loadPagenationData(pagenumber);
//                }
//            }
//        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if(dy > 0)
//                {
//                    visibleitemcount = manager.getChildCount();
//                    totalitemcount = manager.getItemCount();
//                    pastvisibleitems = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
//
////if loading is true which means there is data to be fetched from the database
//
//                    if(loading)
//                    {
//                        if((visibleitemcount + pastvisibleitems) >= totalitemcount)
//                        {
//                            swipeRefreshLayout.setRefreshing(true);
//                            loading = false;
//                            pagenumber += 1;
////                            if(new CheckInter().checkInternet(PagenationDataActivity.this))
////                            {
//                                loadPagenationData(pagenumber);
//
////                            }
//                        }
//                    }
//                }
//            }
//        });
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
////                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
////                {
////                    isScrolling = true;
////                }
////                if (newState == recyclerView.getChildAt(0).getMeasuredHeight() - recyclerView.getMeasuredHeight()) {
////                    page++;
////                    progressBar.setVisibility(View.VISIBLE);
////                    loadPagenationData(page);
////                }
//            }
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////                currentItems = manager.getChildCount();
////                totalItems = manager.getItemCount();
////                scrollOutItems = manager.findFirstVisibleItemPosition();
//
////                if(isScrolling && (currentItems + scrollOutItems == totalItems))
////                {
////                    isScrolling = false;
////                if (dy == recyclerView.getChildAt(0).getMeasuredHeight() - recyclerView.getMeasuredHeight()) {
//                    page++;
//                    progressBar.setVisibility(View.VISIBLE);
//                    loadPagenationData(page);
////                }
////                }
//            }
//        });
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        progressBar = findViewById(R.id.progressbar1);
        loadPagenationData(page,limit);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    loadPagenationData(page, limit);
                }
            }
        });

    }
//    private  void fetchdata()
//    {
//        progressBar = findViewById(R.id.progressbar1);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0;i<10;i++)
//                {
//                    loadPagenationData();
//                    progressBar.setVisibility(View.GONE);
//                }
//
//            }
//        },5000);
//
//    }

    private void loadPagenationData(final int page, int limit)
    {
//        final String pageno=Integer.toString(page);
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            progressBar.setVisibility(View.GONE);
            return;
        }

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

                                if (obj.getInt("status") == 1) {

                                    lstpagedata = new ArrayList<>();
                                    dataArray = obj.getJSONArray("user_data");
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        Log.d("strrrrr", "inside for");

                                            PagenationData pagenationData = new PagenationData();
//                                          if (i<=limit) {
                                            JSONObject objsub = dataArray.getJSONObject(i);
                                            pagenationData.setItem_id(objsub.getInt("item_id"));
                                            pagenationData.setItem_proname(objsub.getString("product_name"));
                                            pagenationData.setItem_proprice(objsub.getString("product_price"));
                                            pagenationData.setItem_image(objsub.getString("cover_image"));

                                        lstpagedata.add(pagenationData);


                                    }

//                                    copy_lstpagedata.addAll(lstpagedata);

                                    Recycler();

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
                params.put("user_id", "58");
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");
                params.put("page",String.valueOf(page));
//                params.put("itemcount",String.valueOf(itemcount));

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
        

    }

}
