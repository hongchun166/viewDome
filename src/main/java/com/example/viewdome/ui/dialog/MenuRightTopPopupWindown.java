package com.example.viewdome.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.viewdome.R;
import com.example.viewdome.utils.ShareUtil;

/**
 * Created by TianHongChun on 2016/5/4.
 */
public class MenuRightTopPopupWindown extends PopupWindow {

    Context context;

    Button setttingButton,shareButton,helpButton;

    public MenuRightTopPopupWindown(Context context) {
        super(context);
        initView(context);
    }

    private  void initView(Context context){
        this.context=context;
        LayoutInflater inflater=LayoutInflater.from(context);
        View rootView=inflater.inflate(R.layout.dialog_righttop_menu_popupwindown, null);
        setttingButton=(Button)rootView.findViewById(R.id.button_settting);
        shareButton=(Button)rootView.findViewById(R.id.button_share);
        helpButton=(Button)rootView.findViewById(R.id.button_help);

        setttingButton.setOnClickListener(onClickListener);
        shareButton.setOnClickListener(onClickListener);
        helpButton.setOnClickListener(onClickListener);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x10000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setContentView(rootView);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_settting:
                    break;
                case R.id.button_share:
                    ShareUtil.shareText(v.getContext());
                    break;
                case R.id.button_help:
                    break;
            }
        }
    };



}
