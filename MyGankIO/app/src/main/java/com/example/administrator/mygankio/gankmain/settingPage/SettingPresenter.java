package com.example.administrator.mygankio.gankmain.settingPage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tdfz on 2017/10/21.
 */

public class SettingPresenter implements SettingContact.Presenter {
    SettingContact.View fragment;
    @Override
    public void start() {

    }
    public SettingPresenter (SettingContact.View fragment){
        this.fragment = checkNotNull(fragment);
    }
}
