package com.example.viewdome.ui.view.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.viewdome.R;

/**
 * Created by TianHongChun on 2016/5/7.
 * 滑动删除listview
 */
public class SwipeListView extends ListView {


    Context context;
    SwipeItemView mFocusedItemView;
    SwipeItemView tempFocusedItemView;

    public SwipeListView(Context context) {
        super(context);
        init(context);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    private void log(String content){
        Log.i(SwipeListView.class.getName(),content);
    }
    private void init(Context context){
            this.context=context;
    }



    public void shrinkListItem(int position) {
        View item = getChildAt(position);
        if (item != null) {
            try {
                ((SwipeItemView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    int mLastX,mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean isIntercept=false;
        int x=(int)event.getX();
        int y=(int)event.getY();
        setEnabled(true);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int position=pointToPosition(x,y);
                if(position!=INVALID_POSITION){
                    int fristPosition=getFirstVisiblePosition();
                    mFocusedItemView=(SwipeItemView)getChildAt(position- fristPosition);
                }
                if( tempFocusedItemView != mFocusedItemView && tempFocusedItemView!=null){
                    if(tempFocusedItemView.isOpen()){
                        tempFocusedItemView.shrink();
                        tempFocusedItemView.setIsOpen(false);
                        isIntercept=true;
                    }
                }
                else if( tempFocusedItemView == mFocusedItemView && mFocusedItemView!=null ){
                    View view=mFocusedItemView.findViewById(R.id.holder);
                    if(view != null && x<(view.getX()-view.getWidth())){
                        if(mFocusedItemView.isOpen()){
                            mFocusedItemView.shrink();
                            mFocusedItemView.setIsOpen(false);
                            isIntercept=true;
                        }
                    }
////                      log("x:"+x+","+";;"+"view.x:"+(view.getX()-view.getWidth())+",");
                }
                tempFocusedItemView=mFocusedItemView;
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(getX()-mLastX)>10){
//                    截取父控件获取ontouch
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                break;
        }
        mLastY=y;
        mLastX=x;
        if(isIntercept){
            return true;
        }
        if(mFocusedItemView!=null){
            mFocusedItemView.onRequireTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

//    @Override
//    public boolean performItemClick(View view, int position, long id) {
//        SwipeItemView1 swipeItemView1=((SwipeItemView1) view);
//        if(swipeItemView1 != null && swipeItemView1.isOpen()){
//            swipeItemView1.shrink();
//            return true;
//        }
//        return super.performItemClick(view, position, id);
//    }
}
