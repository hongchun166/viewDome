package com.example.viewdome.ui.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.viewdome.R;

/**
 * Created by TianHongChun on 2016/5/10.
 */
public class BottomMenuDialog extends BaseDialog {

    Context context;
    int  heightPixels,widthPixels;
    View backView;
    LinearLayout oneLinearLayout,twoLinearLayout,threeLinearLayout;
    int duration=600;

    private static BottomMenuDialog bottomMenuDialog;
    public static BottomMenuDialog getInstance(){
        if(bottomMenuDialog==null){
            bottomMenuDialog=new BottomMenuDialog();
        }
        return bottomMenuDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DisplayMetrics displayMetrics= activity.getResources().getDisplayMetrics();
          heightPixels=displayMetrics.heightPixels;
         widthPixels=displayMetrics.widthPixels;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogStyleFullscreen);
        setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Black_NoTitleBar);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_bottommenu,container,false);
        initView(view);
        startAnim();
        return view;
    }

    private void initView(View view){
            backView=view.findViewById(R.id.back);
            oneLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_one);
            twoLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_two);
            threeLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_three);

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(this!=null&&isVisible()){
                        dismiss();
                }
            }
        });

    }
    private void startAnim(){

        ObjectAnimator animator0 = ObjectAnimator.ofFloat("", "", 1, 0);
        animator0.setDuration(duration+200);
        animator0.setInterpolator(new OvershootInterpolator());
        animator0.start();
        animator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator arg0) {
                float value = (Float) arg0.getAnimatedValue();
                float tranY = value * (160 + 0.0f);
                backView.setRotationX(0.5f);
                backView.setRotationY(0.5f);
                backView.setRotation(tranY);
            }
        });

        ObjectAnimator animator1 = ObjectAnimator.ofFloat("", "", 1, 0);
        long dur2 = duration + 100;
        long dur3 = dur2 + 100;
        animator1.setDuration(duration);
        animator1.setInterpolator(new OvershootInterpolator());
        animator1.start();
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator arg0) {
                float value = (Float) arg0.getAnimatedValue();
                float tranY = value * (heightPixels + 0.0f);
                oneLinearLayout.setTranslationY(tranY);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat("", "", 1, 0);
        animator2.setDuration(dur2);
        animator2.setInterpolator(new OvershootInterpolator());
        animator2.start();
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator arg0) {
                float value = (Float) arg0.getAnimatedValue();
                float tranY = value * (heightPixels + 0.0f);
                twoLinearLayout.setTranslationY(tranY);
            }
        });

        ObjectAnimator animator3 = ObjectAnimator.ofFloat("", "", 1, 0);
        animator3.setDuration(dur3);
        animator3.setInterpolator(new OvershootInterpolator());
        animator3.start();
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator arg0) {
                float value = (Float) arg0.getAnimatedValue();
                float tranY = value * (heightPixels + 0.0f);
                threeLinearLayout.setTranslationY(tranY);
            }
        });
    }

    public void show(FragmentManager fragmentManager){
        super.show(fragmentManager,"BottomMenuDialog");
    }

    private void log(String string){
        Log.i(BottomMenuDialog.class.getName(),""+string);
    }
}
