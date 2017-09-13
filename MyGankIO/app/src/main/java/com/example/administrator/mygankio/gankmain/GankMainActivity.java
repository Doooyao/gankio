package com.example.administrator.mygankio.gankmain;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.gankmain.homePage.HomePresenter;
import com.example.administrator.mygankio.gankmain.searchpage.SearchFragment;
import com.example.administrator.mygankio.gankmain.homePage.HomeFragment;
import com.example.administrator.mygankio.gankmain.searchpage.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class GankMainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewpager;
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
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(searchFragment);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(mainFragmentPagerAdapter);
    }

    private void initview() {
        viewpager = (ViewPager) findViewById(R.id.viewpager_main);
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
        }
    }
}
