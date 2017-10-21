package com.example.administrator.mygankio.gankmain.searchpage.searchallpage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.gggswiperefresh.GGGSwipeRefreshLayout;
import com.example.administrator.mygankio.data.GankType;
import com.example.administrator.mygankio.gankmain.homePage.SpaceItemDecoration;
import com.example.administrator.mygankio.utils.Densityutils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/10/17.
 */

public class SearchCategoryFragment extends Fragment implements SearchCategoryContract.View {


    SearchCategoryContract.Presenter presenter;
    private View root;
    GGGSwipeRefreshLayout gggSwipeRefreshLayout;
    private RecyclerView recycleView;

    public void setPageCategory(String pageCategory) {
        this.pageCategory = pageCategory;
    }

    String pageCategory = GankType.ALL;


    @Override
    public void setPresenter(SearchCategoryContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.search_categorygank_layout,container,false);
        initview();
        presenter.start();
        setlistener();
        return root;
    }

    private void initview() {
        gggSwipeRefreshLayout = (GGGSwipeRefreshLayout) root.findViewById(R.id.search_all_gggswiperefreshlayout);
        recycleView = (RecyclerView)root.findViewById(R.id.search_all_recycleview);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        gggSwipeRefreshLayout.setEnabled(false);
//        recycleView.addItemDecoration(new RecycleViewDivider(
//                getContext(), LinearLayoutManager.VERTICAL));
        recycleView.addItemDecoration(new SpaceItemDecoration(Densityutils.dip2px(getContext(),8)));
    }

    private void setlistener() {
        gggSwipeRefreshLayout.setOnLoadingMoreListener(new GGGSwipeRefreshLayout.OnLoadingMoreListener() {
            @Override
            public void onLoadingMore() {
                presenter.loadMoreGank();
            }
        });
    }

    @Override
    public void showRefreshingBar() {
        gggSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissRefreshingBar() {
        gggSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingMoreBar() {
        gggSwipeRefreshLayout.showLoadingMore();
    }

    @Override
    public void dismissLoadingMoreBar() {
        gggSwipeRefreshLayout.dismissLoadingMore();
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        recycleView.setAdapter(adapter);
    }

    @Override
    public void showBlankBackground(int backgroundType) {

    }

    @Override
    public void showList() {

    }

    @Override
    public String getCategory() {
        return pageCategory;
    }

    @Override
    public void onResume() {
        System.out.println(getCategory());
        super.onResume();
    }
    public SearchCategoryContract.Presenter getPresenter() {
        return presenter;
    }
    @Override
    public Context takeContext() {
        return getContext();
    }


}
