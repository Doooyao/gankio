package com.example.administrator.mygankio.customview.gggswiperefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.example.administrator.mygankio.customview.FootRecycleAdapter;

/**
 * Created by tdfz on 2017/10/16.
 */

public class GGGSwipeRefreshLayout extends SwipeRefreshLayout{
    boolean isloadingmore = false;
    FootRecycleAdapter footRecycleAdapter;
    OnLoadingMoreListener onLoadingMoreListener;
    RecyclerView recyclerView ;
    Context context;
    LoadingViewController loadingViewController;
    public GGGSwipeRefreshLayout(Context context) {
        super(context,null);
    }
    public GGGSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initdata(context);
    }

    private void initdata(Context c) {
        this.context = c;
        loadingViewController = new LoadingViewController(context);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View v = getChildAt(0);
        if (v instanceof RecyclerView){
            recyclerView = (RecyclerView) v;
            if (recyclerView.getAdapter() instanceof FootRecycleAdapter){
                footRecycleAdapter = (FootRecycleAdapter) recyclerView.getAdapter();
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                    final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                     recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                        if (linearLayoutManager.findLastVisibleItemPosition()==footRecycleAdapter.getItemCount()-1&&
                                footRecycleAdapter.isFootViewExist()){
                            if (onLoadingMoreListener!=null){
                                onLoadingMoreListener.onLoadingMore();
                            }
                        }
                }
            });
        } }
        }
    }

    public void dismissLoadingMore(){
        footRecycleAdapter.dismissFootView();
    }
    public void showLoadingMore(){
        footRecycleAdapter.showFootView();
    }
    public interface OnLoadingMoreListener{
        void onLoadingMore();
    }
    public void setOnLoadingMoreListener(OnLoadingMoreListener onLoadingMoreListener){
        this.onLoadingMoreListener = onLoadingMoreListener;
    }
    public boolean getIsLoadingMore(){
        return isloadingmore;
    }
}
