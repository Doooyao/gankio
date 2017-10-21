package com.example.administrator.mygankio.gankmain;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.ViewPagerSlide;
import com.example.administrator.mygankio.gankmain.homePage.HomePresenter;
import com.example.administrator.mygankio.gankmain.photoPopupwindow.MyPhotoPopupWindow;
import com.example.administrator.mygankio.gankmain.searchpage.SearchFragment;
import com.example.administrator.mygankio.gankmain.homePage.HomeFragment;
import com.example.administrator.mygankio.gankmain.searchpage.SearchPresenter;
import com.example.administrator.mygankio.gankmain.settingPage.SettingFragment;
import com.example.administrator.mygankio.gankmain.settingPage.SettingPresenter;

import java.util.ArrayList;
import java.util.List;

public class GankMainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPagerSlide viewpager;
    ImageView ivHome;
    ImageView ivSearch;
    ImageView ivSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gankmain_activity);
        init();
    }

    private void init() {
        initview();
        initFragment();
    }

    private void initFragment() {
        SearchFragment searchFragment = new SearchFragment();
        SearchPresenter searchPresenter = new SearchPresenter(searchFragment);
        HomeFragment homeFragment = new HomeFragment();
        HomePresenter homePresenter = new HomePresenter(homeFragment);
        SettingFragment settingFragment = new SettingFragment();
        SettingPresenter settingPresenter = new SettingPresenter(settingFragment);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(searchFragment);
        fragments.add(settingFragment);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(mainFragmentPagerAdapter);
        viewpager.setOffscreenPageLimit(2);
    }

    private void initview() {
        viewpager = (ViewPagerSlide) findViewById(R.id.viewpager_main);
        ivHome = (ImageView) findViewById(R.id.iv_homebutton);
        ivSearch = (ImageView) findViewById(R.id.iv_searchbutton);
        ivSetting = (ImageView) findViewById(R.id.iv_settingbutton);
        ivHome.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_homebutton:
                viewpager.setCurrentItem(0);
                break;
            case R.id.iv_searchbutton:
                viewpager.setCurrentItem(1);
                break;
            case R.id.iv_settingbutton:
                viewpager.setCurrentItem(2);
        }
    }
}
