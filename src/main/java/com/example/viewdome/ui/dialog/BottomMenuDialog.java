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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.viewdome.R;
import com.example.viewdome.ui.anim.RotateAndTranslateAnimation;

/**
 * Created by TianHongChun on 2016/5/10.
 */
public class BottomMenuDialog extends BaseDialog {

    Context context;
    int  heightPixels=0;
    int widthPixels=0;
    View backView;
    LinearLayout oneLinearLayout,twoLinearLayout,threeLinearLayout;
    View oneImageView,twoImageView,threeImageView;
    View itemRootView;//装载item的view

    int ANIM_TYPE_Rotation=1;
    int ANIM_TYPE_Translation=2;

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
            View rootView=view.findViewById(R.id.rootview);

            itemRootView=view.findViewById(R.id.rootview_item);

            backView=view.findViewById(R.id.back);
            oneLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_one);
            twoLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_two);
            threeLinearLayout=(LinearLayout) view.findViewById(R.id.linearLayout_three);

            oneImageView=view.findViewById(R.id.imageview_one);
            twoImageView=view.findViewById(R.id.imageview_two);
            threeImageView=view.findViewById(R.id.imageview_three);

        backView.setOnClickListener(onClickListener);
        oneLinearLayout.setOnClickListener(onClickListener);
        twoLinearLayout.setOnClickListener(onClickListener);
        threeLinearLayout.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                clearAnim();
                switch (v.getId()){
                    case R.id.back:{

                        float fx=itemRootView.getX()/2;
                        float tx=fx;
                        float fy=itemRootView.getY();
                        float ty=backView.getY();
                        Interpolator interpolator=new AccelerateInterpolator();
                        Animation rootAnimation=createShrinkAnimation(fx,tx,fy,ty,1,800,interpolator,false);

                        rootAnimation.setAnimationListener(animationListener);

                        itemRootView.startAnimation(rootAnimation);

                        Animation itemAnimation=createItemAnimation(2000,false,true,true,true,true);
                        oneImageView.startAnimation(itemAnimation);
                        twoImageView.startAnimation(itemAnimation);
                        threeImageView.startAnimation(itemAnimation);
                        backView.startAnimation(createBackAnimation(false));
                        break;
                    }
                    case R.id.linearLayout_one: {
                        Animation   animation=createItemAnimation(600,true,false,false,true,false);
                            oneImageView.startAnimation(animation);
                        break;
                    }
                    case R.id.linearLayout_two: {
                        twoImageView.startAnimation(createItemAnimation(600,true,false,false,true,false));
                        break;
                    }
                    case R.id.linearLayout_three: {
                        threeImageView.startAnimation(createItemAnimation(600,true,false,false,true,false));
                        break;
                    }
                    default:{

                        break;
                    }
                }
        }
    };

    /**
     * 清除动画
     */
    private void clearAnim(){
        backView.clearAnimation();;
        oneImageView.clearAnimation();
        oneLinearLayout.clearAnimation();
        twoImageView.clearAnimation();
        twoLinearLayout.clearAnimation();
        threeImageView.clearAnimation();
        threeLinearLayout.clearAnimation();
        itemRootView.clearAnimation();


    }
    Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            oneLinearLayout.clearAnimation();
            twoLinearLayout.clearAnimation();
            threeLinearLayout.clearAnimation();
            backView.clearAnimation();
            dismiss();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    private void startAnim(){
        backView.startAnimation(createBackAnimation(true));
        createOpenAnimation(600,oneLinearLayout);
        createOpenAnimation(700,twoLinearLayout);
        createOpenAnimation(800,threeLinearLayout);
    }

    /**
     *  菜单弹出动画
     * @param duration
     * @param view
     * @return
     */
    private ObjectAnimator createOpenAnimation(int duration,final View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat("", "", 1, 0);
        animator.setDuration(duration);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg0) {
                float value = (Float) arg0.getAnimatedValue();
                float tranY = value * (heightPixels + 0.0f);
                view.setTranslationY(tranY);

            }
        });
        return animator;
    }

    /**
     *  item关闭,展开 动画,缩放,透明
     * @param duration
     * @param isClicked
     * @return
     */
    private static Animation createItemAnimation(final long duration, final boolean isClicked,boolean isFillAfter,boolean isScale,boolean isRotate,boolean isAlpha) {
        AnimationSet animationSet = new AnimationSet(true);
        if(isScale){
            animationSet.addAnimation(new ScaleAnimation(1.0f, isClicked ? 2.0f : 0.0f, 1.0f, isClicked ? 2.0f : 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        }
        if(isRotate){
            Animation rotateAnimation = new RotateAnimation( 0,360, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animationSet.addAnimation(rotateAnimation);
        }
        if(isAlpha){
            animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        }

        animationSet.setDuration(duration);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setFillAfter(isFillAfter);
        return animationSet;
    }

    /**
     * back关闭展开动画
     * @param expanded
     * @return
     */
    private static Animation createBackAnimation(final boolean expanded) {
        Animation animation = new RotateAnimation(expanded ? 45 : 0, expanded ? 0 : 45, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setStartOffset(0);
        animation.setDuration(100);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setFillAfter(true);

        return animation;
    }

    /**
     *  itemRootView 关闭动画(也可以做展开)
     * @param fromXDelta
     * @param toXDelta
     * @param fromYDelta
     * @param toYDelta
     * @param startOffset
     * @param duration
     * @param interpolator
     * @param isClicked
     * @return
     */
    private static Animation createShrinkAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                                   long startOffset, long duration, Interpolator interpolator,boolean isClicked) {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);

        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f, isClicked ? 2.0f : 0.0f, 1.0f, isClicked ? 2.0f : 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        TranslateAnimation translateAnimation=new TranslateAnimation(fromXDelta,toXDelta,fromYDelta,toYDelta);
        translateAnimation.setInterpolator(new AccelerateInterpolator());

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(duration);
        return animationSet;
    }



    public void show(FragmentManager fragmentManager){
        super.show(fragmentManager,"BottomMenuDialog");
    }

    private void log(String string){
        Log.i(BottomMenuDialog.class.getName(),""+string);
    }
}
