package com.example.administrator.mygankio;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tdfz on 2017/9/28.
 */

public abstract class BaseRecycleViewAdapter extends Adapter{
    public interface OnItemViewClickLisenter {
        void onItemClick(View view, int position);

        void onItemViewLongClick(View view, int position);

        void onItemViewClick(View view, int position, boolean blick);

    }
    public abstract void setOnItemViewClickListener(OnItemViewClickLisenter onItemViewClickListener) ;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    public abstract void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position);



    public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

    }

}
