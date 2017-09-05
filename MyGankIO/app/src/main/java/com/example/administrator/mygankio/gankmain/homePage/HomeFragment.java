package com.example.administrator.mygankio.gankmain.homePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygankio.R;

/**
 * Created by Administrator on 2017/7/20.
 */

public class HomeFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment,container,false);
        return root;
    }
}
