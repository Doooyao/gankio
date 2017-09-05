package com.example.administrator.mygankio.data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class GankBean {


    /**
     * category : ["瞎推荐","前端","Android","休息视频","福利","iOS"]
     * error : false
     * results : {"Android":[{"_id":"595df22b421aa90ca3bb6ab4","createdAt":"2017-07-06T16:17:47.759Z","desc":"多进程安全的SharedPreferences","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"Android","url":"https://github.com/grandcentrix/tray","used":true,"who":"galois"},{"_id":"595eda10421aa90ca3bb6ab7","createdAt":"2017-07-07T08:47:12.524Z","desc":"Android智能下拉刷新框架，支持越界回弹，集成了几十种炫酷的Header和 Footer。","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"Android","url":"https://github.com/scwang90/SmartRefreshLayout","used":true,"who":"代码家"},{"_id":"595efa18421aa90ca209c409","createdAt":"2017-07-07T11:03:52.882Z","desc":"Android Samba 协议实现。","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"Android","url":"https://github.com/google/samba-documents-provider","used":true,"who":"代码家"},{"_id":"595efa62421aa90cb4724b85","createdAt":"2017-07-07T11:05:06.66Z","desc":"支持引导路径高亮的方案。","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"Android","url":"https://github.com/TakuSemba/Spotlight","used":true,"who":"代码家"},{"_id":"595efd67421aa90c9203d341","createdAt":"2017-07-07T11:17:59.342Z","desc":"Refactoring an existing Android app to support Instant App","publishedAt":"2017-07-07T12:14:57.685Z","source":"web","type":"Android","url":"https://blog.mindorks.com/refactoring-an-existing-android-app-to-support-instant-app-aa11fe54fd73","used":true,"who":"AMIT SHEKHAR"}],"iOS":[{"_id":"595efb1a421aa90c9203d340","createdAt":"2017-07-07T11:08:10.658Z","desc":"macOS 下的数字货币查看工具。","images":["http://img.gank.io/d8de97f9-68c7-4082-af57-e95fbd88a815"],"publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"iOS","url":"https://github.com/DingdingKim/CoinNow","used":true,"who":"代码家"},{"_id":"595efb65421aa90ca209c40a","createdAt":"2017-07-07T11:09:25.758Z","desc":"Simple AR game made with ARKit and SpriteKit. ","images":["http://img.gank.io/17edc452-1dac-4c73-b53c-30b8ff6ee5c1"],"publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"iOS","url":"https://github.com/eh3rrera/ARKitGameSpriteKit","used":true,"who":"S"}],"休息视频":[{"_id":"595e3f40421aa90cb4724b82","createdAt":"2017-07-06T21:46:40.350Z","desc":"九个神奇的磁力小道具","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av11702675/","used":true,"who":"LHF"}],"前端":[{"_id":"595cb961421aa90cb4724b6a","createdAt":"2017-07-05T18:03:13.583Z","desc":"产品级的开源调研系统","images":["http://img.gank.io/4bfe6da5-8f9a-41bb-b2c3-681678e94038"],"publishedAt":"2017-07-07T12:14:57.685Z","source":"web","type":"前端","url":"https://github.com/wkeyuan/DWSurvey","used":true,"who":"Ke Yuan"}],"瞎推荐":[{"_id":"595b4d54421aa90ca209c3ec","createdAt":"2017-07-04T16:09:56.934Z","desc":"Egg-mongo 插件，基于 MongoDB Native Driver，仅供官方党享用。","publishedAt":"2017-07-07T12:14:57.685Z","source":"web","type":"瞎推荐","url":"https://github.com/brickyang/egg-mongo","used":true,"who":"Brick"}],"福利":[{"_id":"595ed766421aa90ca209c407","createdAt":"2017-07-07T08:35:50.172Z","desc":"7-7","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fhb0t7ob2mj20u011itd9.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<AndroidBean> Android;
        private List<IOSBean> iOS;
        private List<休息视频Bean> 休息视频;
        private List<前端Bean> 前端;
        private List<瞎推荐Bean> 瞎推荐;
        private List<福利Bean> 福利;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<休息视频Bean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<休息视频Bean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<前端Bean> get前端() {
            return 前端;
        }

        public void set前端(List<前端Bean> 前端) {
            this.前端 = 前端;
        }

        public List<瞎推荐Bean> get瞎推荐() {
            return 瞎推荐;
        }

        public void set瞎推荐(List<瞎推荐Bean> 瞎推荐) {
            this.瞎推荐 = 瞎推荐;
        }

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        public static class AndroidBean {
            /**
             * _id : 595df22b421aa90ca3bb6ab4
             * createdAt : 2017-07-06T16:17:47.759Z
             * desc : 多进程安全的SharedPreferences
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : chrome
             * type : Android
             * url : https://github.com/grandcentrix/tray
             * used : true
             * who : galois
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
        }

        public static class IOSBean {
            /**
             * _id : 595efb1a421aa90c9203d340
             * createdAt : 2017-07-07T11:08:10.658Z
             * desc : macOS 下的数字货币查看工具。
             * images : ["http://img.gank.io/d8de97f9-68c7-4082-af57-e95fbd88a815"]
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : chrome
             * type : iOS
             * url : https://github.com/DingdingKim/CoinNow
             * used : true
             * who : 代码家
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
        }

        public static class 休息视频Bean {
            /**
             * _id : 595e3f40421aa90cb4724b82
             * createdAt : 2017-07-06T21:46:40.350Z
             * desc : 九个神奇的磁力小道具
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : chrome
             * type : 休息视频
             * url : http://www.bilibili.com/video/av11702675/
             * used : true
             * who : LHF
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
        }

        public static class 前端Bean {
            /**
             * _id : 595cb961421aa90cb4724b6a
             * createdAt : 2017-07-05T18:03:13.583Z
             * desc : 产品级的开源调研系统
             * images : ["http://img.gank.io/4bfe6da5-8f9a-41bb-b2c3-681678e94038"]
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : web
             * type : 前端
             * url : https://github.com/wkeyuan/DWSurvey
             * used : true
             * who : Ke Yuan
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
        }

        public static class 瞎推荐Bean {
            /**
             * _id : 595b4d54421aa90ca209c3ec
             * createdAt : 2017-07-04T16:09:56.934Z
             * desc : Egg-mongo 插件，基于 MongoDB Native Driver，仅供官方党享用。
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : web
             * type : 瞎推荐
             * url : https://github.com/brickyang/egg-mongo
             * used : true
             * who : Brick
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
        }

        public static class 福利Bean {
            /**
             * _id : 595ed766421aa90ca209c407
             * createdAt : 2017-07-07T08:35:50.172Z
             * desc : 7-7
             * publishedAt : 2017-07-07T12:14:57.685Z
             * source : chrome
             * type : 福利
             * url : https://ws1.sinaimg.cn/large/610dc034ly1fhb0t7ob2mj20u011itd9.jpg
             * used : true
             * who : daimajia
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
        }
    }
}
