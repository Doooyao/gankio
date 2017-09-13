package com.example.administrator.mygankio.gankmain.homePage;

import android.app.VoiceInteractor;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.customview.CategoryTab;
import com.example.administrator.mygankio.data.ApiService;
import com.example.administrator.mygankio.data.GankContentHistory;
import com.example.administrator.mygankio.data.GankDateDataBean;
import com.example.administrator.mygankio.data.GankPushDate;

import java.util.ArrayList;
import java.util.List;

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

public class HomePageListAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private Retrofit retrofit;
    private ApiService apiService;
    Context context;
    GankPushDate gankPushDate;
    int positionCounter;
    List<GankDateDataBean> gankDateDataBeanList;


    public HomePageListAdapter(Context context){
        this.context = context;
        positionCounter = 0;
        gankPushDate = new GankPushDate();
        gankDateDataBeanList = new ArrayList<>();
        initRetrofit();
        findGankPushDate();
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
    private void findGankPushDate() {
        apiService.getGankPushDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<GankPushDate>() {
                    @Override
                    public void accept(@NonNull GankPushDate gankPushDate1) throws Exception {
                        gankPushDate = gankPushDate1;
                        setGankDateDataBeanListInPosition(gankPushDate,0,9);
                    }
                });
    }

    //获取制定数量位置的干货
    void setGankDateDataBeanListInPosition(final GankPushDate gankPushDate, final int startPosition, final int endPosition){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                for (int i = startPosition;i<=endPosition;i++){
                    e.onNext(gankPushDate.getResults().get(i).replace("-","/"));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull String s) throws Exception {
                        if (s.equals(completeString)){
                            return true;
                        }else {
                            return apiService.getGankByDate(s);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof GankDateDataBean){
                            gankDateDataBeanList.add((GankDateDataBean) o);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                        if (startPosition == 0){
                            positionCounter = positionCounter+ 11;
                        }else {
                            positionCounter = positionCounter+10;
                        }
                        notifyDataSetChanged();
                    }
                });
    }
    public GankPushDate getGankPushDate() {
        return gankPushDate;
    }

    public void setGankPushDate(GankPushDate gankPushDate) {
        this.gankPushDate = gankPushDate;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View v = LayoutInflater.from(context).inflate(R.layout.home_listitem_recommend,parent,false);
                return new MyViewHolderRecommend(v);
            case 1:
                View v1 = LayoutInflater.from(context).inflate(R.layout.home_listitem_video,parent,false);
                return new MyViewHolderVideo(v1);
            default:
                View v3 = LayoutInflater.from(context).inflate(R.layout.home_listitem_last,parent,false);
                return new MyViewHolderBody(v3);
        }

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolderRecommend){
            final MyViewHolderRecommend recommendHolder = (MyViewHolderRecommend)holder;
            setRecomendTitle(gankPushDate.getResults().get(0).replace("-","/"), recommendHolder);
            recommendHolder.ivSpread.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable d;
                    if (!recommendHolder.beSpread){
                        d = context.getDrawable(R.drawable.animated_down);
                    }else {
                        d = context.getDrawable(R.drawable.animated_up);
                    }
                    ((ImageView)v).setImageDrawable(d);
                    if (d instanceof AnimatedVectorDrawable){
                        ((AnimatedVectorDrawable)d).start();
                        recommendHolder.beSpread = !recommendHolder.beSpread;
                    }
                }
            });



        }else if (holder instanceof MyViewHolderBody){
            String date = gankPushDate.getResults().get(position-1).replace("-","/");
            ((MyViewHolderBody) holder).tvDate.setText(date);
            //加载gankdatabean
            //// TODO: 2017/9/13 下次添加是否存在的判断
            String imgUrl = gankDateDataBeanList.get(position-1).getResults().get福利().get(0).getUrl();
            Glide.with(context)
                    .load(imgUrl)
                    .centerCrop()
                    .into(((MyViewHolderBody) holder).ivBackGround);
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return 2;
        }
    }

    @Override
    public int getItemCount() {
        return positionCounter;
    }


    /***
     *  四个viewholder
     */
    public class MyViewHolderBody extends RecyclerView.ViewHolder {
        ImageView ivBackGround;
        TextView tvDate;
        LinearLayout llTabContainer;


        public MyViewHolderBody(View itemView) {
            super(itemView);
            ivBackGround = (ImageView) itemView.findViewById(R.id.home_item_background);
            llTabContainer = (LinearLayout)itemView.findViewById(R.id.ll_category_tab_container);
            tvDate = (TextView) itemView.findViewById(R.id.home_item_tv_lastdate);
        }
    }
    public class MyViewHolderRecommend extends RecyclerView.ViewHolder {
        boolean beSpread = false;
        TextView tvTitle;
        TextView tvDate;
        ImageView ivSpread;
        RelativeLayout rlContent;
        public MyViewHolderRecommend(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.home_item_recommend_date);
            tvTitle = (TextView) itemView.findViewById(R.id.home_item_recommend_tv);
            ivSpread = (ImageView) itemView.findViewById(R.id.home_item_recommend_spread);
            rlContent= (RelativeLayout) itemView.findViewById(R.id.home_item_recommend_content);
        }
    }
    public class MyViewHolderVideo extends RecyclerView.ViewHolder {
        public MyViewHolderVideo(View itemView) {
            super(itemView);
        }
    }
}
