package com.example.administrator.mygankio.gankmain.searchpage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.data.GankSearchBean;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Administrator on 2017/7/21.
 */

public class SearchFragment extends Fragment implements SearchContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    SearchContract.Presenter searchPresenter;
    RecyclerView recyclerView;
    ImageView ivSearch;
    EditText editTextSearch;
    private SearchListAdapter searchListAdapter;
    private List<GankSearchBean.ResultsBean> been;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment,container,false);
        recyclerView = (RecyclerView) root.findViewById(R.id.search_recycleview);
        ivSearch = (ImageView) root.findViewById(R.id.iv_search);
        editTextSearch = (EditText) root.findViewById(R.id.et_query);
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout_update);
        initSwipeRefreshLayout();
        ivSearch.setOnClickListener(this);
        initRecycleView();
        return root;
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#ff0000"));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchPresenter.start();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        searchPresenter = checkNotNull(presenter);
    }



    public void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        been = new ArrayList<>();
        searchListAdapter = new SearchListAdapter(been,getContext());
        recyclerView.setAdapter(searchListAdapter);
    }


    @Override
    public List<GankSearchBean.ResultsBean> getResultsBeen() {
        return been;
    }

    @Override
    public void showRefreshRecycleView() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshRecycleView() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshRecycleview() {
        searchListAdapter.notifyDataSetChanged();
    }

    @Override
    public Bundle getSearchParam() {
        Bundle b = new Bundle();
        String query = editTextSearch.getText().toString().trim();
        b.putString("query",query);
        return b;
    }


    @Override
    public void onClick(View v) {
        if (v.getId()!=R.id.et_query){
            editTextSearch.clearFocus();
        }
        switch (v.getId()){
            case R.id.iv_search:
                searchPresenter.searchGank();
                break;

        }

    }

    @Override
    public void onRefresh() {
        searchPresenter.searchGank();
    }
}
