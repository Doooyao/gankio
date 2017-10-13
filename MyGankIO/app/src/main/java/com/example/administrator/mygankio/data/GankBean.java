package com.example.administrator.mygankio.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class GankBean implements Parcelable{

    /**
     * _id : 59ad6186421aa901c1c0a8df
     * createdAt : 2017-09-04T22:21:58.464Z
     * desc : MacOS版微信小助手 功能: 自动回复、消息防撤回、远程控制、微信多开
     * images : ["http://img.gank.io/879d6bdd-a345-44a9-8870-1a96fb883f0c"]
     * publishedAt : 2017-09-05T11:29:05.240Z
     * source : web
     * type : App
     * url : https://github.com/TKkk-iOSer/WeChatPlugin-MacOS
     * used : true
     * who : TK
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private String srcUrl;

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(createdAt);
        dest.writeString(desc       );
        dest.writeString(publishedAt);
        dest.writeString(source  );
        dest.writeString(type     );
        dest.writeString(url      );
        dest.writeByte((byte) (used ? 1 : 0));
    }
    public static final Parcelable.Creator<GankBean> CREATOR
            = new Parcelable.Creator<GankBean>() {
        public GankBean createFromParcel(Parcel in) {
            return new GankBean(in);
        }

        public GankBean[] newArray(int size) {
            return new GankBean[size];
        }
    };
    private GankBean(Parcel in) {
        _id         = in.readString();
        createdAt  = in.readString();
        desc       = in.readString();
        publishedAt= in.readString();
        source     = in.readString();
        type      = in.readString();
        url       = in.readString();
        used      = in.readByte()!= 0;
    }
}
