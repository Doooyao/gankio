package com.example.administrator.mygankio.gankmain.settingPage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mygankio.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/10/20.
 */

public class SettingFragment extends Fragment implements SettingContact.View {
    SettingContact.Presenter presenter;
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.setting_fragment,container,false);
        initview();
        return rootview;
    }

    private void initview() {
        TextView tvHelp = (TextView) rootview.findViewById(R.id.tv_help);
        tvHelp.setMovementMethod(LinkMovementMethod.getInstance());
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String data="https://github.com/Doooyao/gankio";
                intent.setData(Uri.parse(data));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(SettingContact.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
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
}
