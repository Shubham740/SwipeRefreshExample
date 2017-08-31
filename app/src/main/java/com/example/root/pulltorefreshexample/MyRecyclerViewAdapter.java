package com.example.root.pulltorefreshexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham on 31/08/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<String> mCityNamesList;

    public MyRecyclerViewAdapter(ArrayList<String> cityNamesList) {
        mCityNamesList = cityNamesList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        String name = (String) mCityNamesList.get(position);
        holder.textViewCityName.setText(String.valueOf(name));

    }

    @Override
    public int getItemCount() {
        return (null != mCityNamesList ? mCityNamesList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCityName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.textViewCityName = (TextView) itemView.findViewById(R.id.textViewcityNameId);

        }
    }

    void refreshCityList(List<String> newList) {
        mCityNamesList.clear();
        mCityNamesList.addAll(newList);
    }

}
