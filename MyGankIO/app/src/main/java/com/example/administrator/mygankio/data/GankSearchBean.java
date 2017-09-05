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
    private List<ResultsBean> results;

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

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : 手机安全小卫士(预防家家，从手机做起)
         * ganhuo_id : 56cc6d22421aa95caa707949
         * publishedAt : 2015-10-19T03:47:21.651000
         * readability : <div><article class="markdown-body entry-content"><h2><a id="user-content-mobilesafer-&#x624B;&#x673A;&#x5B89;&#x5168;&#x5C0F;&#x536B;&#x58EB;&#x9879;&#x76EE;&#x8BF4;&#x660E;" class="anchor" href="https://github.com/msAndroid/MobileSafer#mobilesafer-&#x624B;&#x673A;&#x5B89;&#x5168;&#x5C0F;&#x536B;&#x58EB;&#x9879;&#x76EE;&#x8BF4;&#x660E;"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>MobileSafer (&#x624B;&#x673A;&#x5B89;&#x5168;&#x5C0F;&#x536B;&#x58EB;)&#x9879;&#x76EE;&#x8BF4;&#x660E;</h2>

         <ul>
         <li>MobileSafer&#x4EC5;&#x7528;&#x4F5C;&#x5B66;&#x4E60;&#x4EA4;&#x6D41;&#xFF1B;</li>
         <li>MobileSafer(&#x5B89;&#x5168;&#x5C0F;&#x536B;&#x58EB;)&#x9879;&#x76EE;&#x53D6;&#x6750;&#x81EA;&#x300A;Android&#x9879;&#x76EE;&#x5B9E;&#x6218;-&#x624B;&#x673A;&#x5B89;&#x5168;&#x536B;&#x58EB;&#x300B;&#x4E00;&#x4E66;&#xFF1B;</li>
         <li>&#x5728;&#x4E66;&#x672C;&#x77E5;&#x8BC6;&#x57FA;&#x7840;&#x4E0A;&#x4FEE;&#x6539;&#x800C;&#x6210;&#xFF0C;&#x540C;&#x65F6;&#x540E;&#x671F;&#x5C06;&#x5F15;&#x5165;Material Design&#x98CE;&#x683C;&#xFF0C;&#x5E76;&#x6839;&#x636E;&#x81EA;&#x5DF1;&#x7684;&#x7406;&#x89E3;&#x6DFB;&#x52A0;&#x548C;&#x4FEE;&#x6539;&#x67D0;&#x4E9B;&#x529F;&#x80FD;&#xFF1B;</li>
         <li>&#x5B9A;&#x671F;&#x66F4;&#x65B0;&#x4EE3;&#x7801;&#x53CA;&#x535A;&#x5BA2;&#xFF0C;&#x6709;&#x5174;&#x8DA3;&#x7684;&#x540C;&#x5B66;&#x53EF;&#x4EE5;<code>Follow</code>,&#x4E5F;&#x53EF;&#x4EE5;&#x5173;&#x6CE8;&#x4E2A;&#x4EBA;&#x5FAE;&#x535A;&#xFF0C;&#x535A;&#x5BA2;&#x53CA;&#x516C;&#x4F17;&#x53F7;&#xFF1B;</li>
         </ul>



         <h2><a id="user-content-&#x672C;&#x9879;&#x76EE;" class="anchor" href="https://github.com/msAndroid/MobileSafer#&#x672C;&#x9879;&#x76EE;"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>&#x672C;&#x9879;&#x76EE;&#xFF1A;</h2>

         <div class="highlight highlight-text-xml"><pre>&#x57FA;&#x672C;&#x4E0A;&#x6DB5;&#x76D6;&#x4E86;Android&#x5F00;&#x53D1;&#x7684;&#x57FA;&#x672C;&#x77E5;&#x8BC6;&#x70B9;&#xFF0C;&#x540C;&#x65F6;&#x9879;&#x76EE;&#x4E2D;&#x7528;&#x5230;&#x4E86;&#x51E0;&#x4E2A;&#x5E38;&#x7528;&#x7684;&#x7B2C;&#x4E09;&#x65B9;&#x5F00;&#x6E90;&#x5E93;&#xFF0C;&#x53EF;&#x4F5C;&#x4E3A;&#x63D0;&#x9AD8;&#x4E2A;&#x4EBA;&#x80FD;&#x529B;&#x7684;&#x5B9E;&#x6218;&#x9879;&#x76EE;&#xFF0C;</pre></div>

         <h2><a id="user-content-mobilesafer&#x4E3B;&#x8981;&#x5305;&#x62EC;&#x4E5D;&#x5927;&#x6A21;&#x5757;" class="anchor" href="https://github.com/msAndroid/MobileSafer#mobilesafer&#x4E3B;&#x8981;&#x5305;&#x62EC;&#x4E5D;&#x5927;&#x6A21;&#x5757;"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>MobileSafer&#x4E3B;&#x8981;&#x5305;&#x62EC;&#x4E5D;&#x5927;&#x6A21;&#x5757;&#xFF1A;</h2>

         <ul>
         <li><p>&#x624B;&#x673A;&#x9632;&#x76D7;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x901A;&#x8BAF;&#x536B;&#x58EB;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x8F6F;&#x4EF6;&#x7BA1;&#x5BB6;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x624B;&#x673A;&#x6740;&#x6BD2;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x7F13;&#x5B58;&#x6E05;&#x7406;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x8FDB;&#x7A0B;&#x7BA1;&#x7406;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x6D41;&#x91CF;&#x7EDF;&#x8BA1;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x9AD8;&#x7EA7;&#x5DE5;&#x5177;&#x6A21;&#x5757;&#xFF1A;</p></li>
         <li><p>&#x8BBE;&#x7F6E;&#x4E2D;&#x5FC3;&#x6A21;&#x5757;&#xFF1A;</p></li>
         </ul>

         <h2><a id="user-content-changelog" class="anchor" href="https://github.com/msAndroid/MobileSafer#changelog"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>CHANGELOG</h2>

         <h2><a id="user-content-110" class="anchor" href="https://github.com/msAndroid/MobileSafer#110"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>1.1.0</h2>

         <ul>
         <li>&#x5B8C;&#x6210;&#x6B22;&#x8FCE;&#x754C;&#x9762;&#x53CA;&#x68C0;&#x67E5;&#x7248;&#x672C;&#x66F4;&#x65B0;&#x903B;&#x8F91;(&#x672A;&#x5B9E;&#x73B0;Service&#x5B9A;&#x671F;&#x68C0;&#x67E5;&#x66F4;&#x65B0;);</li>
         <li>&#x5F15;&#x5165;&#x4E86;XUtils;</li>
         </ul>

         <p><a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/splash.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/splash.png" alt="Drawing" width="200px"></a>
         <a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/updatedialog.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/updatedialog.png" alt="Drawing" width="200px"></a>
         <a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/download.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/download.png" alt="Drawing" width="200px"></a>
         <a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/install.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/install.png" alt="Drawing" width="200px"></a>
         <a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/home.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/home.png" alt="Drawing" width="200px"></a></p>

         <h2><a id="user-content-111" class="anchor" href="https://github.com/msAndroid/MobileSafer#111"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>1.1.1</h2>



         <p><a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/screenshot_setpwd.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/screenshot_setpwd.png" alt="Drawing" width="200px"></a>
         <a href="https://github.com/msAndroid/MobileSafer/blob/master/app/src/main/res/drawable/screenshot_interpwd.png" target="_blank"><img src="https://github.com/msAndroid/MobileSafer/raw/master/app/src/main/res/drawable/screenshot_interpwd.png" alt="Drawing" width="200px"></a></p>



         <h2><a id="user-content-&#x5173;&#x4E8E;&#x6211;" class="anchor" href="https://github.com/msAndroid/MobileSafer#&#x5173;&#x4E8E;&#x6211;"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>&#x5173;&#x4E8E;&#x6211;</h2>



         <h2></h2>

         <p>
         <a href="https://github.com/JueYingCoder/UseAsyncTask/blob/master/app/src/main/res/mipmap-xxhdpi/weixin.jpg" target="_blank"><img src="https://github.com/JueYingCoder/UseAsyncTask/raw/master/app/src/main/res/mipmap-xxhdpi/weixin.jpg"></a>
         </p>

         <h2><a id="user-content-license" class="anchor" href="https://github.com/msAndroid/MobileSafer#license"><svg class="octicon octicon-link" height="16" width="16"><path></path></svg></a>License</h2>

         <pre><code>Copyright 2015 florent37, Inc.

         Licensed under the Apache License, Version 2.0 (the "License");
         you may not use this file except in compliance with the License.
         You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

         Unless required by applicable law or agreed to in writing, software
         distributed under the License is distributed on an "AS IS" BASIS,
         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
         See the License for the specific language governing permissions and
         limitations under the License.
         </code></pre>
         </article>
         </div>
         * type : Android
         * url : https://github.com/msAndroid/MobileSafer
         * who : 有时放纵
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
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

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
