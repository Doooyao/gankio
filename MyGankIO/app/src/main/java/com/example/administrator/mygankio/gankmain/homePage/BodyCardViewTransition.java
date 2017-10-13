package com.example.administrator.mygankio.gankmain.homePage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tdfz on 2017/10/10.
 */

public class BodyCardViewTransition extends Transition {


    private static final String WIDTH = "width";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        View view = transitionValues.view;

            transitionValues.values.put(WIDTH,view.getWidth());

    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        transitionValues.values.put(WIDTH,view.getWidth());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, final TransitionValues endValues) {
        if (startValues == null || endValues == null) { return null;}
        final View endView = endValues.view;
        if (!(endView instanceof CardView)){return null;}
        final float endRadius ;
        final float startRadius;
        final int startWidth = (int) startValues.values.get(WIDTH);
        final int endWidth = (int) endValues.values.get(WIDTH);
        if (startWidth>endWidth){
            startRadius = 4;
            endRadius = endWidth/2;
        }else {
            endRadius = 4;
            startRadius =startWidth/2;
        }
        ((CardView) endView).setRadius(startRadius);
        ValueAnimator secondAnimator = ValueAnimator.ofFloat(startRadius, endRadius);
        secondAnimator.setDuration(1000);
        secondAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float current = (float) valueAnimator.getAnimatedValue();
                ((CardView) endView).setRadius(current);
            }
        });


//        ValueAnimator firstAnimator = ValueAnimator.ofFloat(startElevation, endElevation);
//        firstAnimator.setDuration(2500);
//        firstAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                float current = (float) valueAnimator.getAnimatedValue();
//                ViewCompat.setTranslationZ(endView,current);
//            }
//        });
//
        AnimatorSet set = new AnimatorSet();
//        set.play(secondAnimator).after(firstAnimator);
        set.play(secondAnimator);
        return set;
    }
}
