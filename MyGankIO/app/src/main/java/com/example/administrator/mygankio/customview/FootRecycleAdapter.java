package com.example.administrator.mygankio.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.administrator.mygankio.R;

import java.util.List;


/**
 * Created by tdfz on 2017/10/16.
 */

public class FootRecycleAdapter extends RecyclerView.Adapter {
   public static final int TYPE_FOOT = -1;
    boolean isFootViewExist = false;
    public static final int CIRCLE_FOOT = 0;
    public static final int HOR_FOOT = 1;
    int foottype = 1;
    Context context;
    public FootRecycleAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_FOOT){
            View v;
            if (foottype == CIRCLE_FOOT){
                v = LayoutInflater.from(context).inflate(R.layout.myprogressbar_circle,parent,false);
            }else {
               v = LayoutInflater.from(context).inflate(R.layout.myprogressbar_hor,parent,false);
            }
            RecyclerView.ViewHolder viewHolder = new FootViewHolder(v);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public int addFootViewCount(int i){
        if (isFootViewExist){
            return i+1;
        }else {
            return i;
        }
    }
    public class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFootViewExist&&position==getItemCount()-1){
            return TYPE_FOOT;
        }
        return super.getItemViewType(position);
    }
    public boolean isFootViewExist(){
        return isFootViewExist;
    }
    public void showFootView(){
        if (!isFootViewExist){
            isFootViewExist=true;
            notifyItemInserted(getItemCount());
        }
    }
    public void dismissFootView(){
        if (isFootViewExist){
            isFootViewExist=false;
            notifyItemRemoved(getItemCount()-1);
        }
    }
    public void choiceFootType(int foottype){
        this.foottype = foottype;
    }

    public interface OnLoadingDataListener{
        void onStart();
        void onComplete(List result);
        void onNotEnoughData(List result);
    }
}
