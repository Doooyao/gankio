package com.example.administrator.mygankio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tdfz on 2017/9/27.
 */

public interface IHolderBinder<T> {
    void bindHolder(RecyclerView.ViewHolder holder, T t);
    void init(Context context);
}
