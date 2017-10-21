package com.example.administrator.mygankio.gankmain.searchpage.searchallpage;

import android.support.annotation.NonNull;

import com.example.administrator.mygankio.customview.FootRecycleAdapter;
import com.example.administrator.mygankio.data.GankBean;
import com.example.administrator.mygankio.data.GankTaker;
import com.example.administrator.mygankio.data.GankType;
import com.example.administrator.mygankio.data.IGankTaker;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/10/17.
 */

public class SearchCategoryPresenter implements SearchCategoryContract.Presenter {
    int searchcount = 10;
    int searchpage = 1;
    String searchCategory = GankType.ALL;
    String searchQuery = "";
    SearchCategoryContract.View fragment;
    private SearchCategoryListAdapter searchAllListAdapter;
    GankTaker gankTaker;

    public SearchCategoryPresenter(@NonNull SearchCategoryContract.View searchAllFragment){
        this.fragment = checkNotNull(searchAllFragment,"searchallfragment cannot b null");
        fragment.setPresenter(this);
        gankTaker = GankTaker.create();
    }
    @Override
    public void start() {
        searchAllListAdapter = new SearchCategoryListAdapter(fragment);
        searchAllListAdapter.choiceFootType(FootRecycleAdapter.CIRCLE_FOOT);
        fragment.setAdapter(searchAllListAdapter);
    }


    @Override
    public void searchGank(String query) {
        searchQuery = query;
        gankTaker.searchGank(searchQuery, searchcount, searchpage, fragment.getCategory(),
                new IGankTaker.OnSearchFinishListener() {
                    @Override
                    public void onstart() {
                        fragment.showRefreshingBar();
                    }

                    @Override
                    public void onfinish(List<GankBean> list) {
                        if (list.size()<searchcount){
                            fragment.dismissLoadingMoreBar();
                        }else {
                            fragment.showLoadingMoreBar();
                        }
                        searchAllListAdapter.getGankBeanList().clear();
                        searchAllListAdapter.getGankBeanList().addAll(list);
                        searchAllListAdapter.notifyDataSetChanged();
                        fragment.dismissRefreshingBar();
                    }
                });
    }

    @Override
    public void loadMoreGank() {
        int currentPage = searchAllListAdapter.getGankBeanList().size()/searchcount;
        System.out.println(currentPage);
        gankTaker.searchGank(searchQuery, searchcount, currentPage + 1, fragment.getCategory(),
                new IGankTaker.OnSearchFinishListener() {
                    @Override
                    public void onstart() {

                    }

                    @Override
                    public void onfinish(List<GankBean> list) {
                        if (list.size()<searchcount){
                            fragment.dismissLoadingMoreBar();
                        }else {
                            fragment.showLoadingMoreBar();
                        }
                        searchAllListAdapter.getGankBeanList().addAll(list);
                        searchAllListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
