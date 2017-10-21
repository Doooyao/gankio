package com.example.administrator.mygankio.data;

import java.util.List;

/**
 * Created by tdfz on 2017/10/18.
 */

public interface IGankTaker {
    void searchGank(String query,int count,int page, String category,OnSearchFinishListener onSearchFinishListener);
    interface OnSearchFinishListener{
        void onstart();
        void onfinish(List<GankBean> list);
    }
}
