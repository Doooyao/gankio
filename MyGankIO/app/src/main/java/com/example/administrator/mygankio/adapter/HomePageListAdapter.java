package com.example.administrator.mygankio.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.CategoryTab;
import com.example.administrator.mygankio.data.GankDateDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class HomePageListAdapter extends RecyclerView.Adapter <HomePageListAdapter.MyViewHolder>{
    Context context;
    List<GankDateDataBean> gankDateDataBeanList;
    public HomePageListAdapter(Context context){
        this.context = context;
        gankDateDataBeanList = new ArrayList<>();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_listitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context)
                .load(gankDateDataBeanList.get(position).getResults().get福利().get(0).getUrl())
                .centerCrop()
                .into(holder.getIvBackGround());
        LinearLayout ll = holder.getLlTabContainer();
        ll.removeAllViews();
        if (gankDateDataBeanList.get(position).getResults().getAndroid()!=null){
            CategoryTab categoryTab = new CategoryTab(context);
            categoryTab.setattr(64,"Android", Color.parseColor("#ff00ff"));
            ll.addView(categoryTab);
        }
        if (gankDateDataBeanList.get(position).getResults().getiOS()!=null){
            CategoryTab categoryTab = new CategoryTab(context);
            categoryTab.setattr(64,"IOS", Color.parseColor("#5566ff"));
            ll.addView(categoryTab);
        }
        if (gankDateDataBeanList.get(position).getResults().get休息视频()!=null){
            CategoryTab categoryTab = new CategoryTab(context);
            categoryTab.setattr(64,"休息视频", Color.parseColor("#445676"));
            ll.addView(categoryTab);
        }
        if (gankDateDataBeanList.get(position).getResults().get拓展资源()!=null){
            CategoryTab categoryTab = new CategoryTab(context);
            categoryTab.setattr(64,"拓展资源", Color.parseColor("#f244ff"));
            ll.addView(categoryTab);
        }
        if (gankDateDataBeanList.get(position).getResults().get瞎推荐()!=null){
            CategoryTab categoryTab = new CategoryTab(context);
            categoryTab.setattr(64,"瞎推荐", Color.parseColor("#998871"));
            ll.addView(categoryTab);
        }


    }

    @Override
    public int getItemCount() {
        return gankDateDataBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBackGround;

        public LinearLayout getLlTabContainer() {
            return llTabContainer;
        }

        public void setLlTabContainer(LinearLayout llTabContainer) {
            this.llTabContainer = llTabContainer;
        }

        LinearLayout llTabContainer;

        public ImageView getIvBackGround() {
            return ivBackGround;
        }

        public void setIvBackGround(ImageView ivBackGround) {
            this.ivBackGround = ivBackGround;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            ivBackGround = (ImageView) itemView.findViewById(R.id.home_item_background);
            llTabContainer = (LinearLayout)itemView.findViewById(R.id.ll_category_tab_container);
        }
    }
    public List<GankDateDataBean> getGankDateDataBeanList(){
        return gankDateDataBeanList;
    }
}
