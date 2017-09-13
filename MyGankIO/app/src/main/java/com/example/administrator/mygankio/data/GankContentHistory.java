package com.example.administrator.mygankio.data;

import java.util.List;

/**
 * Created by tdfz on 2017/9/6.
 */

public class GankContentHistory {

    /**
     * error : false
     * results : [{"_id":"59af76fb421aa901c1c0a8e6","content":"<p><img alt=\"\" src=\"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg\" /><\/p>\r\n\r\n<h3>Android<\/h3>\r\n\r\n<ul>\r\n\t<li><a href=\"http://www.jianshu.com/p/4bb16cefca23\" target=\"_blank\">召唤，光能使者--玩转PathMeasure<\/a>&nbsp;(Vivian)\r\n\r\n\t<ul>\r\n\t\t<li><a href=\"http://www.jianshu.com/p/4bb16cefca23\" target=\"_blank\"><img src=\"http://img.gank.io/f2ab16f6-68f7-4030-adcb-d2cccced9c9f\" title=\"召唤，光能使者--玩转PathMeasure\" /><\/a><\/li>\r\n\t<\/ul>\r\n\t<\/li>\r\n\t<li><a href=\"https://github.com/QMUI/QMUI_Android\" target=\"_blank\">QMUI 团队出品，一个致力于提高 Android 项目 UI 开发效率的解决方案<\/a>&nbsp;(chanthuang)\r\n\t<ul>\r\n\t<\/ul>\r\n\t<\/li>\r\n\t<li><a href=\"https://mp.weixin.qq.com/s/BwjyJwUJKZSSaaKbzRiT8Q\" target=\"_blank\">Android Google ARCore尝鲜记录<\/a>&nbsp;(D_clock)\r\n\t<ul>\r\n\t<\/ul>\r\n\t<\/li>\r\n\t<li><a href=\"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&amp;mid=2247486804&amp;idx=1&amp;sn=664b30f6bdbd55f3b8be706a9a4fe092\" target=\"_blank\">Android彻底组件化源码分析<\/a>&nbsp;(陈宇明)\r\n\t<ul>\r\n\t<\/ul>\r\n\t<\/li>\r\n\t<li><a href=\"https://github.com/zybieku/SoftKeyboardUtil\" target=\"_blank\">一行代码实现软键盘与EditText的交互<\/a>&nbsp;(朱能权)\r\n\t<ul>\r\n\t\t<li><a href=\"https://github.com/zybieku/SoftKeyboardUtil\" target=\"_blank\"><img src=\"http://img.gank.io/ea3059f0-23b7-4cf9-be74-82d9d995107a\" title=\"一行代码实现软键盘与EditText的交互\" /><\/a><\/li>\r\n\t<\/ul>\r\n\t<\/li>\r\n<\/ul>\r\n\r\n<h3>前端<\/h3>\r\n\r\n<ul>\r\n\t<li><a href=\"https://github.com/minirefresh/minirefresh\" target=\"_blank\">优雅的H5下拉刷新【minirefresh】<\/a>&nbsp;(Lichun Dai)\r\n\r\n\t<ul>\r\n\t\t<li><a href=\"https://github.com/minirefresh/minirefresh\" target=\"_blank\"><img src=\"http://img.gank.io/84984b11-7c2a-419d-ba2b-b2f11fb3fc00\" title=\"优雅的H5下拉刷新【minirefresh】\" /><\/a><\/li>\r\n\t<\/ul>\r\n\t<\/li>\r\n<\/ul>\r\n\r\n<h3>休息视频<\/h3>\r\n\r\n<ul>\r\n\t<li><a href=\"http://video.sina.com.cn/p/news/s/doc/2017-08-28/101866970037.html?\" target=\"_blank\">德国网红Slivki发现了中国网购 一发不可收拾<\/a>&nbsp;(lxxself)\r\n\r\n\t<ul>\r\n\t<\/ul>\r\n\t<\/li>\r\n<\/ul>\r\n\r\n<p>感谢所有默默付出的编辑们，愿大家有美好一天。<\/p>\r\n","created_at":"2017-09-06T12:18:03.155Z","publishedAt":"2017-09-06T12:16:00.0Z","rand_id":"24e9549d-fb28-4f21-bc82-bf32a674c0c3","title":"今日力推：一行代码实现软键盘与EditText的交互 / 提高 Android 项目 UI 开发效率的解决方案","updated_at":"2017-09-06T12:18:03.155Z"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59af76fb421aa901c1c0a8e6
         * content : <p><img alt="" src="https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg" /></p>

         <h3>Android</h3>

         <ul>
         <li><a href="http://www.jianshu.com/p/4bb16cefca23" target="_blank">召唤，光能使者--玩转PathMeasure</a>&nbsp;(Vivian)

         <ul>
         <li><a href="http://www.jianshu.com/p/4bb16cefca23" target="_blank"><img src="http://img.gank.io/f2ab16f6-68f7-4030-adcb-d2cccced9c9f" title="召唤，光能使者--玩转PathMeasure" /></a></li>
         </ul>
         </li>
         <li><a href="https://github.com/QMUI/QMUI_Android" target="_blank">QMUI 团队出品，一个致力于提高 Android 项目 UI 开发效率的解决方案</a>&nbsp;(chanthuang)
         <ul>
         </ul>
         </li>
         <li><a href="https://mp.weixin.qq.com/s/BwjyJwUJKZSSaaKbzRiT8Q" target="_blank">Android Google ARCore尝鲜记录</a>&nbsp;(D_clock)
         <ul>
         </ul>
         </li>
         <li><a href="https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&amp;mid=2247486804&amp;idx=1&amp;sn=664b30f6bdbd55f3b8be706a9a4fe092" target="_blank">Android彻底组件化源码分析</a>&nbsp;(陈宇明)
         <ul>
         </ul>
         </li>
         <li><a href="https://github.com/zybieku/SoftKeyboardUtil" target="_blank">一行代码实现软键盘与EditText的交互</a>&nbsp;(朱能权)
         <ul>
         <li><a href="https://github.com/zybieku/SoftKeyboardUtil" target="_blank"><img src="http://img.gank.io/ea3059f0-23b7-4cf9-be74-82d9d995107a" title="一行代码实现软键盘与EditText的交互" /></a></li>
         </ul>
         </li>
         </ul>

         <h3>前端</h3>

         <ul>
         <li><a href="https://github.com/minirefresh/minirefresh" target="_blank">优雅的H5下拉刷新【minirefresh】</a>&nbsp;(Lichun Dai)

         <ul>
         <li><a href="https://github.com/minirefresh/minirefresh" target="_blank"><img src="http://img.gank.io/84984b11-7c2a-419d-ba2b-b2f11fb3fc00" title="优雅的H5下拉刷新【minirefresh】" /></a></li>
         </ul>
         </li>
         </ul>

         <h3>休息视频</h3>

         <ul>
         <li><a href="http://video.sina.com.cn/p/news/s/doc/2017-08-28/101866970037.html?" target="_blank">德国网红Slivki发现了中国网购 一发不可收拾</a>&nbsp;(lxxself)

         <ul>
         </ul>
         </li>
         </ul>

         <p>感谢所有默默付出的编辑们，愿大家有美好一天。</p>

         * created_at : 2017-09-06T12:18:03.155Z
         * publishedAt : 2017-09-06T12:16:00.0Z
         * rand_id : 24e9549d-fb28-4f21-bc82-bf32a674c0c3
         * title : 今日力推：一行代码实现软键盘与EditText的交互 / 提高 Android 项目 UI 开发效率的解决方案
         * updated_at : 2017-09-06T12:18:03.155Z
         */

        private String _id;
        private String content;
        private String created_at;
        private String publishedAt;
        private String rand_id;
        private String title;
        private String updated_at;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getRand_id() {
            return rand_id;
        }

        public void setRand_id(String rand_id) {
            this.rand_id = rand_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
