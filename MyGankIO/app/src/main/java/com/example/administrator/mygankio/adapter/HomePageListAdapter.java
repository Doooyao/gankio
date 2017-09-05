package com.example.administrator.mygankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.data.GankDateDataBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class HomePageListAdapter extends RecyclerView.Adapter <HomePageListAdapter.MyViewHolder>{
    Context context;
    List<GankDateDataBean> gankDateDataBeanList;
    public HomePageListAdapter(Context context){
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_listitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
