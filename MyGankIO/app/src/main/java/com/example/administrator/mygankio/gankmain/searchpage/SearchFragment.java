package com.example.administrator.mygankio.gankmain.searchpage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.CategoryTabGroup;
import com.example.administrator.mygankio.customview.CircleSpreadBackGround;
import com.example.administrator.mygankio.customview.ViewPagerSlide;
import com.example.administrator.mygankio.data.GankType;
import com.example.administrator.mygankio.gankmain.searchpage.searchallpage.SearchCategoryContract;
import com.example.administrator.mygankio.gankmain.searchpage.searchallpage.SearchCategoryFragment;
import com.example.administrator.mygankio.gankmain.searchpage.searchallpage.SearchCategoryPresenter;


import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Administrator on 2017/7/21.
 */

public class SearchFragment extends Fragment implements SearchContract.View, View.OnClickListener{
    SearchContract.Presenter searchPresenter;
    ImageView ivSearch;
    CircleSpreadBackGround circleSpreadBackGround;
    CategoryTabGroup categoryTabGroup;
    EditText editTextSearch;
    ViewPagerSlide viewPager;
    private View root;
    private List<Integer> barColorList;
    private List<SearchCategoryFragment> fragments;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.search_fragment,container,false);
        initview();
        return root;
    }

    private void initview() {
        ivSearch = (ImageView) root.findViewById(R.id.iv_search);
        editTextSearch = (EditText) root.findViewById(R.id.et_query);
        ivSearch.setOnClickListener(this);
        circleSpreadBackGround = (CircleSpreadBackGround) root.findViewById(R.id.ll_bar);
        circleSpreadBackGround.setColor(getResources().getColor(R.color.colorPrimary));
//        circleSpreadBackGround.setColor(getResources().getColor(R.color.colorPink));
        initColorList();
        categoryTabGroup = (CategoryTabGroup) root.findViewById(R.id.search_bar_category_group);
        initCategoryBar();
        initviewpager();
        setlistener();

    }

    private void initviewpager() {
        viewPager = (ViewPagerSlide) root.findViewById(R.id.viewpager_search);
        initFragment();
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setSlide(false);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0;i<8;i++){
            String category;
            switch (i){
                case 0:
                    category = GankType.ALL;
                    break;
                case 1:
                    category = GankType.ANDROID;
                    break;
                case 2:
                    category = GankType.IOS;
                    break;
                case 3:
                    category = GankType.VIDEO;
                    break;
                case 4:
                    category = GankType.RES;
                    break;
                case 5:
                    category = GankType.WEB;
                    break;
                case 6:
                    category = GankType.RECOMMEND;
                    break;
                default:
                    category = GankType.APP;
                    break;
            }
            SearchCategoryFragment searchAllFragment = new SearchCategoryFragment();
            searchAllFragment.setPageCategory(category);
            SearchCategoryContract.Presenter searchCategoryPresenter = new SearchCategoryPresenter(searchAllFragment);
            fragments.add(searchAllFragment);
        }
    }

    private void initColorList() {
        barColorList = new ArrayList<>();
        int allColor    = getResources().getColor(R.color.colorallColor);
        int androidColor = getResources().getColor(R.color.colorandroidColor);
        int iosColor    = getResources().getColor(R.color.coloriosColor);
        int videoColor = getResources().getColor(R.color.colorVideoColor);
        int otherColor  = getResources().getColor(R.color.colorotherColor);
        int webColor   = getResources().getColor(R.color.colorwebColor);
        int niceColor   = getResources().getColor(R.color.colorniceColor);
        int appColor   = getResources().getColor(R.color.colorappColor);
        barColorList.add( allColor   );
        barColorList.add( androidColor);
        barColorList.add( iosColor   );
        barColorList.add( videoColor );
        barColorList.add( otherColor );
        barColorList.add( webColor   );
        barColorList.add( niceColor  );
        barColorList.add( appColor   );
    }

    private void initCategoryBar() {
        categoryTabGroup.addTab("全部");
        categoryTabGroup.addTab("Android");
        categoryTabGroup.addTab("IOS");
        categoryTabGroup.addTab("休息视频");
        categoryTabGroup.addTab("拓展资源");
        categoryTabGroup.addTab("前端");
        categoryTabGroup.addTab("瞎推荐");
        categoryTabGroup.addTab("App");
        categoryTabGroup.resetTab();
        categoryTabGroup.setCurrentPosition(0);
    }

    private void setlistener() {
        categoryTabGroup.setOnTabClickListener(new CategoryTabGroup.OnTabClickListener() {
            @Override
            public void onTabClick(View v, int position, MotionEvent event) {
                circleSpreadBackGround.changetoAnotherColor(barColorList.get(position),event.getRawX(),event.getRawY());
                viewPager.setCurrentItem(position);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragments.get(viewPager.getCurrentItem()).getPresenter().searchGank(editTextSearch.getText().toString().trim());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        editTextSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    ((InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    searchPresenter.searchGank();
                }
                return false;
            }
        });
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

    @Override
    public void showRefreshingBar() {

    }

    @Override
    public void dismissRefreshingBar() {

    }

    @Override
    public void showLoadingMoreBar() {

    }

    @Override
    public void dismissLoadingMoreBar() {

    }
    @Override
    public void onClick(View v) {
        if (v.getId()!=R.id.et_query){
            editTextSearch.clearFocus();
        }
        switch (v.getId()){
            case R.id.iv_search:
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                searchPresenter.searchGank();
                break;

        }

    }

    @Override
    public int getCurrentPage() {
        return viewPager.getCurrentItem();
    }

    @Override
    public String getQuery() {
        return editTextSearch.getText().toString();
    }

    @Override
    public void searchByAllPage(String query) {
        fragments.get(viewPager.getCurrentItem()).getPresenter().searchGank(query);
    }

    @Override
    public void searchByCollectionPage(String query) {

    }
}
