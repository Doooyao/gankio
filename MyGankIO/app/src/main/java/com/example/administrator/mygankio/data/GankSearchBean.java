package com.example.administrator.mygankio.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class GankSearchBean {

    @SerializedName("count")
    private int _$Count197; // FIXME check this code
    private boolean error;
    private List<GankBean> results;

    public int get_$Count197() {
        return _$Count197;
    }

    public void set_$Count197(int _$Count197) {
        this._$Count197 = _$Count197;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankBean> getResults() {
        return results;
    }

    public void setResults(List<GankBean> results) {
        this.results = results;
    }
}
