package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<SearchFilterModal> list = new ArrayList<>();
    SearchFilterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        SearchFilterModal[] myListData = new SearchFilterModal[] {
                new SearchFilterModal("Email"),
                new SearchFilterModal("Info"),
                new SearchFilterModal("Delete"),
                new SearchFilterModal("Dialer"),
                new SearchFilterModal("Alert"),
                new SearchFilterModal("Map"),
                new SearchFilterModal("Email"),
                new SearchFilterModal("Info"),
                new SearchFilterModal("Delete"),
                new SearchFilterModal("Dialer"),
                new SearchFilterModal("Alert"),
                new SearchFilterModal("Map"),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewfilter);
        adapter = new SearchFilterAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_badge);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        String useriput = newText.toLowerCase();
//        List<String> newList = new ArrayList<>();
//        for (String list : list)
//        {
//            if (list.toLowerCase().contains(useriput))
//            {
//                newList.add(list);
//            }
//        }
//        adapter.updateList(list);
        return false;
    }
}
