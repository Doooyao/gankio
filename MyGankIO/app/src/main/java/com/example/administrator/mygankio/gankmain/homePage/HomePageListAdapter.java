package com.example.administrator.mygankio.gankmain.homePage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.FootRecycleAdapter;
import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankBean;
import com.example.administrator.mygankio.data.GankContentHistory;
import com.example.administrator.mygankio.data.GankDateDataBean;
import com.example.administrator.mygankio.data.GankPushDate;
import com.example.administrator.mygankio.gankmain.photoshowPage.GankItemShowActivity;
import com.example.administrator.mygankio.holderbinder.BannerHolderBinder;
import com.example.administrator.mygankio.utils.Densityutils;
import com.example.administrator.mygankio.utils.StringUtils;
import com.example.administrator.mygankio.utils.WebUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class HomePageListAdapter extends FootRecycleAdapter{
    private Retrofit retrofit;
    private ApiService apiService;
    Context context;
    GankPushDate gankPushDate;
    List<GankDateDataBean> gankDateDataBeanList;
    HomeContract.view fragment;
    static final int BANNER_LOCATION = 0;
    static final int RECOMMEND_LOCATION = 1;
    static final int BANNER_TYPE = 0;
    static final int RECOMMEND_TYPE = 1;
    static final int BODY_TYPE = 2;
    int headDataSize = 0;
    int oneTimeAddCount = 0;
    private OnLoadingMoreFinishListener onLoadingMoreFinishListener;
    private OnRefreshingFinishListener onRefreshingFinishListener;


    public HomePageListAdapter(HomeContract.view fragment,int count){
        super(fragment.takeContext());
        this.context = fragment.takeContext();
        this.fragment = fragment;
        this.oneTimeAddCount = count;
        gankPushDate = new GankPushDate();
        gankDateDataBeanList = new ArrayList<>();
        initRetrofit();
        initListData();
    }
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    //获取发表干货的日期
    public void initListData() {
        apiService.getGankPushDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankPushDate>() {
                    @Override
                    public void accept(@NonNull GankPushDate gankPushDate1) throws Exception {
                        gankPushDate = gankPushDate1;
                        loadDataRange(0, oneTimeAddCount - 1, new OnLoadingDataListener() {
                            @Override
                            public void onStart() {
                                if (onRefreshingFinishListener!=null){
                                    onRefreshingFinishListener.onRefreshingStart();
                                }
                            }

                            @Override
                            public void onComplete(List result) {
                                gankDateDataBeanList.clear();
                                gankDateDataBeanList.addAll(result);
                                notifyDataSetChanged();
                                if (onRefreshingFinishListener!=null){
                                    onRefreshingFinishListener.onRefreshingFinish();
                                }
                            }

                            @Override
                            public void onNotEnoughData(List result) {
                                gankDateDataBeanList.clear();
                                gankDateDataBeanList.addAll(result);
                                notifyDataSetChanged();
                                if (onRefreshingFinishListener!=null){
                                    onRefreshingFinishListener.noMoreData();
                                }
                            }
                        });
                    }
                });
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case BANNER_TYPE:
                View v1 = LayoutInflater.from(context).inflate(R.layout.home_listitem_banner,parent,false);
                return new MyViewHolderBanner(v1);
            case RECOMMEND_TYPE:
                View v = LayoutInflater.from(context).inflate(R.layout.home_listitem_recommend,parent,false);
                return new MyViewHolderRecommend(v);
            case BODY_TYPE:
                View v3 = LayoutInflater.from(context).inflate(R.layout.home_listitem_body,parent,false);
                return new MyViewHolderBody(v3);
            default:
                return super.onCreateViewHolder(parent,viewType);
        }

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolderRecommend){
            //bind recommendholder
            final MyViewHolderRecommend recommendHolder = (MyViewHolderRecommend)holder;
            setRecomendTitle(gankPushDate.getResults().get(0).replace("-","/"), recommendHolder);
//            initGankCategoryGroupList(recommendHolder,gankDateDataBeanList.get(position));
            recommendHolder.cvRootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addGankList(recommendHolder,gankDateDataBeanList.get(0));
                    final LinearLayout llContent = recommendHolder.llContent;
                    if (!recommendHolder.beSpread){
                        //reMeasure
                        llContent.measure(Integer.MAX_VALUE>>2,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        //getHeight
                        int measureHeight = llContent.getMeasuredHeight();
                        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context,R.animator.touch_raise);
                        objectAnimator.setTarget(v);

                        //initAnim&startAnim
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,measureHeight);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                ViewGroup.LayoutParams layoutParams = llContent.getLayoutParams();
                                layoutParams.height = (int) animation.getAnimatedValue();
                                llContent.setLayoutParams(layoutParams);
                            }
                        });
                        valueAnimator.setDuration(250);
                        valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
                        AnimatorSet spreadAnimator = new AnimatorSet();
                        spreadAnimator.playSequentially(objectAnimator,valueAnimator);
                        spreadAnimator.start();
                    }else {
                        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context,R.animator.touch_down);
                        objectAnimator.setTarget(v);
                        //initAnim&startAnim
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(llContent.getMeasuredHeight(),0);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                ViewGroup.LayoutParams layoutParams = llContent.getLayoutParams();
                                layoutParams.height = (int) animation.getAnimatedValue();
                                llContent.setLayoutParams(layoutParams);
                            }
                        });
                        valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
                        valueAnimator.setDuration(250);
                        AnimatorSet unSpreadAnimator = new AnimatorSet();
                        unSpreadAnimator.playSequentially(objectAnimator,valueAnimator);
                        unSpreadAnimator.start();
                    }
                    recommendHolder.beSpread = !recommendHolder.beSpread;
                }
            });
        }else if (holder instanceof MyViewHolderBody){
            if (gankDateDataBeanList.get(position-1)!=null){
            //bind bodyholder
            MyViewHolderBody mbody = (MyViewHolderBody) holder;
            String date = gankPushDate.getResults().get(position-1).replace("-","/").substring(5,10);
            final ImageView ivBackground = mbody.ivBackGround;
            final CardView cardView =  mbody.cardView;
            final CardView gankBtn = mbody.cardGankbtn;
            TextView tvTitle = mbody.tvTitle;
            if (gankDateDataBeanList.get(position-1).getResults().get休息视频()!=null&&
                    gankDateDataBeanList.get(position-1).getResults().get休息视频().size()>0){
                tvTitle.setText(gankDateDataBeanList.get(position-1).getResults().get休息视频().get(0).getDesc());
                tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebUtils.gotoWebActivity(context,gankDateDataBeanList.get(position-1)
                        .getResults().get休息视频().get(0).getUrl(),v);
                    }
                });
            }else {
                tvTitle.setText("本期还没有休息视频呢=。=看官点进来看看内容吧");
            }
            mbody.tvDate.setText(date);
            //加载gankdatabean
            //// TODO: 2017/9/13 下次添加是否存在的判断
            final String imgUrl = gankDateDataBeanList.get(position-1).getResults().get福利().get(0).getUrl();
            //暂时不对categorylist初始化 也不显示 讲道理这个做的有点难看 不要了
            //            initCategoryParent(gankDateDataBeanList.get(position-1),mbody.llCategoryParent);
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .centerCrop()
                    .into(ivBackground);
            ivBackground.setOnClickListener(new View.OnClickListener() {
                 @Override
                public void onClick(View v) {
                   new GetImageCacheTask(context){
                       @Override
                       protected void onPostExecute(File result) {
                           if (result == null) {
                               return;
                           }
                           //此path就是对应文件的缓存路径
                           String path = result.getPath();
                            GankItemShowActivity.launch((AppCompatActivity)context,ivBackground,cardView
                            ,gankBtn,path,gankDateDataBeanList.get(position-1),GankItemShowActivity.FROM_PHOTO);
                       }
                   }.execute(imgUrl);
                }
            });
            mbody.cardGankbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GetImageCacheTask(context){
                        @Override
                        protected void onPostExecute(File result) {
                            if (result == null) {
                                return;
                            }
                            //此path就是对应文件的缓存路径
                            String path = result.getPath();
                            GankItemShowActivity.launch((AppCompatActivity)context,ivBackground,cardView
                                    ,gankBtn,path,gankDateDataBeanList.get(position-1),GankItemShowActivity.FROM_GANK);
                        }
                    }.execute(imgUrl);
                }
            });}
        }

        else if (holder instanceof MyViewHolderBanner){
            // bind bannnerholder
            BannerHolderBinder bannerHolderBinder = new BannerHolderBinder();
            bannerHolderBinder.init(context);
            bannerHolderBinder.bindHolder(holder,gankDateDataBeanList.get(0));
        }else {
            super.onBindViewHolder(holder,position);
        }
    }

    private void initCategoryParent(GankDateDataBean gankDateDataBean, LinearLayout llCategoryParent) {
        llCategoryParent.removeAllViews();
        List<String> categorys = gankDateDataBean.getCategory();
        for (String s:categorys){
            if (s.equals("Android")||s.equals("iOS")||s.equals("前端")){
                ImageView categoryView = new ImageView(context);
                categoryView.setLayoutParams(new LinearLayout.LayoutParams(Densityutils.dip2px(context,24),
                        Densityutils.dip2px(context,24)));
                int rid = StringUtils.getImageResIdByCategoryString(s);
                if (rid!=0){
                    categoryView.setImageResource(rid);
                    llCategoryParent.addView(categoryView);
                }
            }
        }
    }

    private void addGankList(MyViewHolderRecommend recommendHolder, GankDateDataBean gankDateDataBean) {
        recommendHolder.llContent.removeAllViews();
        List<List<GankBean>> lists = gankDateDataBean.getAllData();
        //制定文字总宽度 暂时不用
//        int textWidth = new Densityutils().dip2px(context,
//                context.getResources().getDimension(R.dimen.homepage_card_recommend_textview_width));
        //测量布局宽度用到这里
        int textWidth = recommendHolder.llContent.getWidth();
//        textWidth = textWidth-Densityutils.dip2px(context,32);
        for (List<GankBean> gankBeanList : lists){
            if (gankBeanList!=null&&!gankBeanList.isEmpty()){
                View categoryViewRoot = LayoutInflater.from(context).inflate(
                        R.layout.home_listitem_recommend_catrgoryitem,null);
                //addCategoryTab
                ImageView categoryIcon = (ImageView) categoryViewRoot.findViewById(R.id.home_item_recommend_categoryicon);
                categoryIcon.setImageResource(StringUtils.getImageResIdByCategoryString(gankBeanList.get(0).getType()));
                TextView categoryTitle = (TextView) categoryViewRoot.findViewById(R.id.home_item_recommend_categorytitle);
                categoryTitle.setText(gankBeanList.get(0).getType());
                //setlistener but do nothing
                categoryViewRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                recommendHolder.llContent.addView(categoryViewRoot);
                for (final GankBean gankBean : gankBeanList){
                    //addGankTitle
                    TextView tvTitle = (TextView) LayoutInflater.from(context).inflate(
                            R.layout.home_listitem_recommend_titleitem,null);
                    LinearLayout.LayoutParams tvParams =
                            new LinearLayout.LayoutParams(textWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    tvParams.setMargins(0,Densityutils.dip2px(context,4),0,Densityutils.dip2px(context,4));
                    tvTitle.setLayoutParams(tvParams);
                    String desc = gankBean.getDesc();
                    String viawho = "via."+gankBean.getWho();
                    SpannableString spannableStringDesc = new SpannableString(desc);
                    SpannableString spannableStringWho = new SpannableString("("+viawho+")");
                    RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
                    spannableStringWho.setSpan(sizeSpan,0,viawho.length()+2,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GRAY);
                    spannableStringWho.setSpan(colorSpan,0,viawho.length()+2,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(spannableStringDesc);
                    spannableStringBuilder.append(spannableStringWho);
                    tvTitle.setText(spannableStringBuilder);
                    tvTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebUtils.gotoWebActivity(context,gankBean.getUrl(),v);
                        }
                    });
                    recommendHolder.llContent.addView(tvTitle);
                }
            }
        }
    }


    private void setRecomendTitle(String replace, final MyViewHolderRecommend holder) {
        Observable<GankContentHistory> observable = apiService.getGankContentHistory(replace);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankContentHistory>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GankContentHistory gankContentHistory) {
                        holder.tvTitle.setText(gankContentHistory.getResults().get(0).getTitle());
                        holder.tvDate.setText(gankPushDate.getResults().get(0));
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        System.out.println("errrrrrrrrrrrrrrror");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public int getItemViewType(int position) {
        if (isFootViewExist()&&position==getItemCount()-1){
            return TYPE_FOOT;
        }else {
            switch (position){
                case BANNER_LOCATION:
                    return BANNER_TYPE;
                case RECOMMEND_LOCATION:
                    return RECOMMEND_TYPE;
                default:
                    return BODY_TYPE;
            }
        }
    }


    @Override
    public int getItemCount() {
        return addFootViewCount(gankDateDataBeanList.size()+headDataSize);
    }






    /***
     *  四个viewholder
     */
    public class MyViewHolderBody extends RecyclerView.ViewHolder {
        ImageView ivBackGround;
        TextView tvDate;
        CardView cardView;
        LinearLayout llCategoryParent;
        TextView tvTitle;
        CardView cardGankbtn;

        public MyViewHolderBody(View itemView) {
            super(itemView);
            ivBackGround = (ImageView) itemView.findViewById(R.id.home_item_background);
            cardView = (CardView) itemView.findViewById(R.id.home_item_body_cardview);
//            llTabContainer = (LinearLayout)itemView.findViewById(R.id.ll_category_tab_container);
            tvDate = (TextView) itemView.findViewById(R.id.home_item_tv_lastdate);
            llCategoryParent= (LinearLayout) itemView.findViewById(R.id.home_item_body_category_parent);
            tvTitle = (TextView) itemView.findViewById(R.id.home_item_body_tv);
            cardGankbtn = (CardView) itemView.findViewById(R.id.home_item_body_gankbtn);
        }
    }
    public class MyViewHolderRecommend extends RecyclerView.ViewHolder {
        boolean beSpread = false;
        TextView tvTitle;
        TextView tvDate;
//        ImageView ivSpread;
        CardView cvRootview;
        LinearLayout llContent;
//        LinearLayout llCategoryGroup;
        public MyViewHolderRecommend(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.home_item_recommend_date);
            tvTitle = (TextView) itemView.findViewById(R.id.home_item_recommend_tv);
//            ivSpread = (ImageView) itemView.findViewById(R.id.home_item_recommend_spread);
            cvRootview = (CardView) itemView.findViewById(R.id.home_item_recommend_cardview);
            llContent= (LinearLayout) itemView.findViewById(R.id.home_item_recommend_content);
//            llCategoryGroup = (LinearLayout) itemView.findViewById(R.id.home_item_recommend_categorygroup);
        }
    }

    public class MyViewHolderBanner extends RecyclerView.ViewHolder{

        ViewPager viewPager;

        public ViewPager getViewPager() {
            return viewPager;
        }

        public void setViewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        public MyViewHolderBanner(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.home_item_banner_viewpager);
        }
    }

    private class GetImageCacheTask extends AsyncTask<String, Void, File> {
        private final Context context;

        public GetImageCacheTask(Context context) {
            this.context = context;
        }

        @Override
        protected File doInBackground(String... params) {
            String imgUrl =  params[0];
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception ex) {
                return null;
            }
        }
    }
    //加载更多监听
    public interface OnLoadingMoreFinishListener{
        void onLoadingFinish();
        void noMoreData();
        void onLoadingStart();
    }
    public void setOnLoadingMoreFinishListener(OnLoadingMoreFinishListener onLoadingMoreFinishListener){
        this.onLoadingMoreFinishListener = onLoadingMoreFinishListener;
    }
    //加载首页监听（刷新）
    public interface OnRefreshingFinishListener{
        void onRefreshingFinish();
        void noMoreData();
        void onRefreshingStart();
    }
    public void setOnRefreshingFinishListener(OnRefreshingFinishListener onRefreshingFinishListener){
        this.onRefreshingFinishListener = onRefreshingFinishListener;
    }

    void loadDataRange(final int startPosition, final int endPosition, final OnLoadingDataListener onLoadingDataListener){
            final List<GankDateDataBean> loadGankDateDataBeanList = new ArrayList<>();
            onLoadingDataListener.onStart();
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                    for (int i = startPosition; i <= endPosition; i++) {
                        if (i < gankPushDate.getResults().size()) {
                            e.onNext(gankPushDate.getResults().get(i).replace("-", "/"));
                        } else {
                            e.onComplete();
                        }
                    }
                    e.onComplete();
                }
            }).observeOn(Schedulers.io())
                    .flatMap(new Function<String, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(@NonNull String s) throws Exception {
                            return apiService.getGankByDate(s);
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Object o) {
                            if (o instanceof GankDateDataBean) {
                                loadGankDateDataBeanList.add((GankDateDataBean) o);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            loadGankDateDataBeanList.add(new GankDateDataBean());
                            //// TODO: 2017/10/16 网络出错下次再做
                        }

                        @Override
                        public void onComplete() {
                            if (loadGankDateDataBeanList.size() < endPosition - startPosition + 1) {
                                onLoadingDataListener.onNotEnoughData(loadGankDateDataBeanList);
                            } else {
                                onLoadingDataListener.onComplete(loadGankDateDataBeanList);
                            }
                        }
                    });

    }
    public void loadingMoreGankRange(int startPosition,int endPosition){
        loadDataRange(startPosition, endPosition, new OnLoadingDataListener() {
            @Override
            public void onStart() {
                if (onLoadingMoreFinishListener!=null){
                    onLoadingMoreFinishListener.onLoadingStart();
                }
            }
            @Override
            public void onComplete(List result) {
                gankDateDataBeanList.addAll(result);
                notifyDataSetChanged();
                if (onLoadingMoreFinishListener!=null){
                    onLoadingMoreFinishListener.onLoadingFinish();
                }
            }
            @Override
            public void onNotEnoughData(List result) {
                gankDateDataBeanList.addAll(result);
                notifyDataSetChanged();
                if (onLoadingMoreFinishListener!=null){
                    onLoadingMoreFinishListener.noMoreData();
                }
            }
        });
    }
}
