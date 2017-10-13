package com.example.administrator.mygankio.gankmain.photoshowPage;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import android.support.v4.util.Pair;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygankio.R;
import com.example.administrator.mygankio.data.GankBean;
import com.example.administrator.mygankio.data.GankDateDataBean;
import com.example.administrator.mygankio.gankmain.homePage.BodyCardViewTransition;
import com.example.administrator.mygankio.utils.StringUtils;
import com.example.administrator.mygankio.utils.WebUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GankItemShowActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CardView cardFab;
    private String photoUri;
    private GankDateDataBean gankDateDataBean;
    private View photoShowView;
    static final int SHARE_ELEMENT_DURATION = 2000;
    String fromwhere;
    private View gankShowView;
    private LinearLayout gankParent;
   public final static String FROM_PHOTO = "photo";
   public final static String FROM_GANK = "gank";
    private ImageView photoSrvImageview;
    private CardView photoParent;
    private CardView gankCard;
    private ImageView cardFabImageView;
    private Bitmap bm;
    private ImageView photoDownLoad;

    public static void launch(AppCompatActivity activity, View transitionViewImgView,
                              View transitionViewImgCard,
                              View transitionFabBtn,
                              String imgUri, GankDateDataBean gankDateDataBean,String fromWhere) {
        Intent intent = new Intent(activity, GankItemShowActivity.class);
        intent.putExtra("imgUri",imgUri);
        intent.putExtra("fromwhere",fromWhere);
        intent.putExtra("gankDateDataBean",gankDateDataBean);
        // 这里指定了共享的视图元素
//        Pair<View,String> pImageView = Pair.create(transitionViewImgView,"image");
        Pair<View,String> pCardview = Pair.create(transitionViewImgCard,"cardview");
        Pair<View,String> pCardfab = Pair.create(transitionFabBtn,"fab");

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pCardview,pCardfab);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gankitem_show);
        initdata();
        initview();
        initviewpager();
        if (fromwhere.equals(FROM_PHOTO)){
          setPhotoPageAnim();
        }else if (fromwhere.equals(FROM_GANK)){
            setGankPageAnim();

        }
        initListener();
    }

    private void initListener() {
        cardFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem()==0){

                    viewPager.setCurrentItem(1);
                }else {viewPager.setCurrentItem(0);
                   }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1&&positionOffset==0){
                    cardFabImageView.setAlpha(1f);
                }else {
                    cardFabImageView.setAlpha(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        //启动动画
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

    private void initviewpager() {
        final List<View> views= new ArrayList<>();
        views.add(photoShowView);
        views.add(gankShowView);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        });
    }

    private void initdata() {
        gankDateDataBean = getIntent().getParcelableExtra("gankDateDataBean");
        photoUri = getIntent().getStringExtra("imgUri");
        fromwhere = getIntent().getStringExtra("fromwhere");
    }
    private void bringButtonFront(boolean b){
        if (b){
            viewPager.setElevation(5);
            cardFab.setElevation(10);
        }else {
            viewPager.setElevation(10);
            cardFab.setElevation(5);
        }

    }



    private void initview() {
        viewPager = (ViewPager) findViewById(R.id.gankitem_show_fragmentparent);
        cardFab = (CardView) findViewById(R.id.photo_show_nextbtn);
        gankShowView = LayoutInflater.from(this).inflate(R.layout.show_gank_layout,null);
        gankParent = (LinearLayout) gankShowView.findViewById(R.id.gank_show_gankparent);
        gankCard = (CardView)gankShowView.findViewById(R.id.gank_show_gankcard);
        addGankList(gankParent);
        photoShowView = LayoutInflater.from(this).inflate(R.layout.show_photo_layout,null);
        photoDownLoad = (ImageView)photoShowView.findViewById(R.id.photo_show_download);
        photoParent = (CardView) photoShowView.findViewById(R.id.photo_show_photoparent);
        photoSrvImageview = (ImageView) photoShowView.findViewById(R.id.photo_show_src);
        cardFabImageView = (ImageView)cardFab.findViewById(R.id.photo_show_nextbtnimg);
        FileInputStream fis;
        try {
            fis = new FileInputStream(photoUri);
            bm = BitmapFactory.decodeStream(fis);
            photoSrvImageview.setImageBitmap(bm);
            cardFabImageView.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addGankList(LinearLayout gankParent) {
        List<List<GankBean>> lists = gankDateDataBean.getAllData();
        for (List<GankBean> gankBeanList : lists){
            if (gankBeanList!=null&&!gankBeanList.isEmpty()){
                View categoryViewRoot = LayoutInflater.from(this).inflate(
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
                gankParent.addView(categoryViewRoot);
                for (final GankBean gankBean : gankBeanList){
                    //addGankTitle
                    TextView tvTitle = (TextView) LayoutInflater.from(this).inflate(
                            R.layout.home_listitem_recommend_titleitem,null);
                    LinearLayout.LayoutParams tvParams =
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                            WebUtils.gotoWebActivity(GankItemShowActivity.this,gankBean.getUrl(),v);
                        }
                    });
                    gankParent.addView(tvTitle);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        int position = viewPager.getCurrentItem();
        switch (position){
            case 0:
                setPhotoPageAnim();
                break;
            case 1:
                setGankPageAnim();
                break;
        }
        super.onBackPressed();
    }

    private void setGankPageAnim() {
        bringButtonFront(false);
        viewPager.setCurrentItem(1);
        ChangeBounds transition = new ChangeBounds();
        BodyCardViewTransition transition1 = new BodyCardViewTransition();
//        Transition transition = new Fade();
        ViewCompat.setTransitionName(cardFab,"cardview");
        //在这里添加一些图片加载完成的操作
        ViewCompat.setTransitionName(gankCard, "fab");
//        ViewCompat.setTransitionName(cardFabImageView, "img");
//        transition.addTarget("fab");
//        transition1.addTarget("fab");
//        transition.addTarget("cardview");
//        transition1.addTarget("cardview");
//        transition.addTarget("img");
//        transition1.addTarget("img");
        TransitionSet tinto = new TransitionSet();
        tinto.addTransition(transition);
        tinto.addTransition(transition1);
        getWindow().setSharedElementEnterTransition(tinto);
        getWindow().getSharedElementEnterTransition().setDuration(SHARE_ELEMENT_DURATION);
        getWindow().getSharedElementEnterTransition().setInterpolator(new LinearOutSlowInInterpolator());
        postponeEnterTransition();
        scheduleStartPostponedTransition(gankCard);
    }

    private void setPhotoPageAnim() {
        bringButtonFront(true);
        viewPager.setCurrentItem(0);
        ViewCompat.setTransitionName(cardFab,"fab");
        ViewCompat.setTransitionName(photoDownLoad,"null");
        //在这里添加一些图片加载完成的操作
//        ViewCompat.setTransitionName(photoSrvImageview, "image");
        ViewCompat.setTransitionName(photoParent, "cardview");
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().getSharedElementEnterTransition().setInterpolator(new LinearOutSlowInInterpolator());
        getWindow().getSharedElementEnterTransition().setDuration(SHARE_ELEMENT_DURATION);
        postponeEnterTransition();
        scheduleStartPostponedTransition(photoParent);
    }
}
