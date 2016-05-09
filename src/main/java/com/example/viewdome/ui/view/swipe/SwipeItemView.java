package com.example.viewdome.ui.view.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.viewdome.R;


/**
 * Created by TianHongChun on 2016/5/7.
 * 滑动删除listview itemview
 */
public class SwipeItemView extends LinearLayout {

    Context context;
    Scroller mScroller;//弹性滑动对象
    LinearLayout mHolder;// 滑出的view
    LinearLayout mViewContent;
    private int mHolderWidth=0;
    boolean isHorizontalMove=true;// 判断是否是横向滑动
    boolean isOpen=false;//是否打开的
    int TAN=2;//倍数

    private int mLastX = 0;
    private int mLastY = 0;

    public SwipeItemView(Context context) {
        super(context);
        initView(context);
    }

    public SwipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SwipeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
            this.context=context;
        // 初始化弹性滑动对象
        mScroller = new Scroller(context);
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.item_swiper, this);
        mViewContent = (LinearLayout) findViewById(R.id.view_content);
        mHolder = (LinearLayout) findViewById(R.id.holder);
        mHolder.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        mHolderWidth=mHolder.getMeasuredWidth();
    }

    public void setContentView(View view){
        mViewContent.addView(view);
    }
    public void setHideView(View view){
        mHolder.addView(view);
        mHolder.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        mHolderWidth=mHolder.getMeasuredWidth();
        mHolder.setLayoutParams(new LayoutParams(mHolderWidth,LayoutParams.MATCH_PARENT));
    }

    public void onRequireTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        int scrollX = getScrollX();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                isHorizontalMove = true;//按下默认为true，当移上下则为false
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                //判断是横向还是纵向
                if (Math.abs(deltaX) < Math.abs(deltaY) * TAN) {
                    isHorizontalMove = false;
                    break;
                }else {
                    isHorizontalMove=true;
                }

                int newScrollX = scrollX - deltaX;//滑动的x
                if (deltaX != 0) {
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    } else if (newScrollX > mHolderWidth) {
                        newScrollX = mHolderWidth;
                    }
                    this.scrollTo(newScrollX, 0);
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                int newScrollX = 0;
                if (scrollX - mHolderWidth * 0.60 > 0) {
                    newScrollX = mHolderWidth;
                    setIsOpen(true);
                }else {
                    setIsOpen(false);
                }
                this.smoothScrollTo(newScrollX, 0);
                break;
            }
        }
        mLastX=x;
        mLastY=y;
    }

    public void shrink(){
        if (getScrollX() != 0) {
            this.smoothScrollTo(0, 0);
        }
    }
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 2);
        invalidate();
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
