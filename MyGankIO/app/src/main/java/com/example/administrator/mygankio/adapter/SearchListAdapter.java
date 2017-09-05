package com.example.administrator.mygankio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.data.GankSearchBean;
import com.example.administrator.mygankio.utils.ImgUtrls;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {
    List<GankSearchBean.ResultsBean> resultsBeen;
    Context context;
    public SearchListAdapter(List<GankSearchBean.ResultsBean> resultsBeen, Context context){
        this.resultsBeen = resultsBeen;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_listitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final GankSearchBean.ResultsBean resultsBean = resultsBeen.get(position);
        holder.getTextViewTitle().setText(resultsBean.getDesc());
        holder.getTextViewWho().setText(resultsBean.getWho());
        new ImgUtrls().getImgMiniFromResultBean(resultsBean, new ImgUtrls.OnBitmapGetSuccess() {
            @Override
            public void OnSuccess(String s) {
                Glide.with(context)
                        .load(s)
                        .centerCrop()
                        .into(holder.getImageView());

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewWho;

        public ImageView getImageView() {
            return imageView;
        }

        private ImageView imageView;

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public TextView getTextViewWho() {
            return textViewWho;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.category_listitem_title);
            textViewWho = (TextView) itemView.findViewById(R.id.category_listitem_who);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item1);
        }
    }
}
