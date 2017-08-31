package com.example.root.pulltorefreshexample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<String> mCityNameArrayList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mRecyclerView = (RecyclerView) mSwipeRefreshLayout.findViewById(R.id.recyclerViewId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mCityNameArrayList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.cityNames)));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(mCityNameArrayList);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }

        });


    }

    private void refreshContent() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myRecyclerViewAdapter.refreshCityList(getNewTweets());
                myRecyclerViewAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private List<String> getNewTweets() {
        List<String> newCatNames = new ArrayList<String>();
        for (int start = 0; start < mCityNameArrayList.size(); start++) {
            int randomCatNameIndex = new Random().nextInt(mCityNameArrayList.size() - 1);
            newCatNames.add(mCityNameArrayList.get(randomCatNameIndex));
        }
        return newCatNames;
    }

}
