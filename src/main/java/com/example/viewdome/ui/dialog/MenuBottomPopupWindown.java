package com.example.viewdome.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.viewdome.R;

/**
 * Created by TianHongChun on 2016/5/11.
 */
public class MenuBottomPopupWindown extends PopupWindow {

    Context context;
    View itemRootView;
    Animation showAnim,dismissAnim;

    private static MenuBottomPopupWindown menuBottomPopupWindown;
    public static MenuBottomPopupWindown getInstance(Context context){
            if(menuBottomPopupWindown==null){
                synchronized (MenuBottomPopupWindown.class){
                    if(menuBottomPopupWindown==null){
                        menuBottomPopupWindown=new MenuBottomPopupWindown(context);
                    }
                }
            }
        return menuBottomPopupWindown;
    }

    public MenuBottomPopupWindown(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context){
            this.context=context;
        LayoutInflater inflater=LayoutInflater.from(context);
        View rootView=inflater.inflate(R.layout.dialog_bottom_menu_popupwindown,null);
        itemRootView=rootView.findViewById(R.id.rootview_item);
        rootView.findViewById(R.id.item_one).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.item_two).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.item_three).setOnClickListener(onClickListener);

        rootView.setFocusableInTouchMode(true);

        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(false);
        this.setTouchable(true);
        this.update();
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setContentView(rootView);
        dismissAnim=AnimationUtils.loadAnimation(context,R.anim.menu_bottom_dismiss);
        showAnim=AnimationUtils.loadAnimation(context,R.anim.menu_bottom_show);
     }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        itemRootView.clearAnimation();
        itemRootView.startAnimation(showAnim);
        super.showAtLocation(parent, gravity, x, y);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.item_one:
                   Toast.makeText(v.getContext(),"点击的:item_one",Toast.LENGTH_SHORT).show();
                   break;
               case R.id.item_two:
                   Toast.makeText(v.getContext(),"点击的:item_two",Toast.LENGTH_SHORT).show();
                   break;
               case R.id.item_three:
                   Toast.makeText(v.getContext(),"点击的:item_three",Toast.LENGTH_SHORT).show();
                   break;
           }
        }
    };

}
