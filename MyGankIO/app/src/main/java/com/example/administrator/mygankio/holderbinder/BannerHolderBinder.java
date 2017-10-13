package com.example.administrator.mygankio.holderbinder;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mygankio.IHolderBinder;
import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.data.GankBean;
import com.example.administrator.mygankio.data.GankDateDataBean;
import com.example.administrator.mygankio.gankmain.homePage.HomePageListAdapter;
import com.example.administrator.mygankio.utils.GetImgUrlListener;
import com.example.administrator.mygankio.utils.ImgUtrls;
import com.example.administrator.mygankio.utils.StringUtils;
import com.example.administrator.mygankio.utils.WebUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdfz on 2017/9/27.
 */

public class BannerHolderBinder implements IHolderBinder {
    final static int VP_OFFSCREEN_LIMIT = 1;
    Context context;
    @Override
    public void bindHolder(RecyclerView.ViewHolder holder, Object o) {
        if (holder instanceof HomePageListAdapter.MyViewHolderBanner&& o instanceof GankDateDataBean){
            HomePageListAdapter.MyViewHolderBanner viewHolder = (HomePageListAdapter.MyViewHolderBanner) holder;
            GankDateDataBean gankDateDataBean = (GankDateDataBean) o;
            ViewPager viewPager = viewHolder.getViewPager();
            viewPager.setOffscreenPageLimit(VP_OFFSCREEN_LIMIT);
            final List<View> views = new ArrayList<>();
            initViewList(gankDateDataBean,views);
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return views.size();
//                    return Integer.MAX_VALUE;
                }
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view==object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {

                    container.addView(views.get(position % views.size()));
                    return views.get(position % views.size());
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView(views.get(position % views.size()));
                }
            });
//            viewPager.setCurrentItem(100*views.size());
        }
    }

    private void initViewList(final GankDateDataBean g, List<View> views) {


        View videoView = LayoutInflater.from(context).inflate(R.layout.home_listitem_bannervideo,null,false);
        TextView videoTitle = (TextView) videoView.findViewById(R.id.home_item_video_title);
        final ImageView videoSrc = (ImageView) videoView.findViewById(R.id.home_item_video_img);
        videoTitle.setText(g.getResults().get休息视频().get(0).getDesc());
        ImgUtrls.getImgUrlFromUrl(g.getResults()
                .get休息视频().get(0).getUrl(), new GetImgUrlListener() {
            @Override
            public void getImgUrl(String s) {
                Glide.with(context)
                        .load(StringUtils.formatUrl(s))
                        .centerCrop()
                        .into(videoSrc);

            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = g.getResults().get休息视频().get(0).getUrl();
                WebUtils.gotoWebActivity(context,url,v.getRootView());
            }
        });
        views.add(videoView);
            for (GankBean gankBean:g.getResults().get福利()){
                View photoView = LayoutInflater.from(context).inflate(R.layout.home_listitem_bannerphoto,null,false);
               TextView photoTitle = (TextView) photoView.findViewById(R.id.home_item_photo_title);
               ImageView photoSrc = (ImageView) photoView.findViewById(R.id.home_item_photo_src);
               photoTitle.setText("via."+gankBean.getWho());
               String imgUrl = StringUtils.formatUrl(gankBean.getUrl());
                Glide.with(context)
                        .load(imgUrl)
                        .centerCrop()
                        .into(photoSrc);
                views.add(photoView);
            }
    }


    @Override
    public void init(Context context) {
        this.context = context;
    }


}
