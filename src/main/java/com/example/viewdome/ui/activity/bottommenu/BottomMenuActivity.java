package com.example.viewdome.ui.activity.bottommenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.dialog.BottomMenuDialog;
import com.example.viewdome.ui.dialog.MenuBottomPopupWindown;

public class BottomMenuActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    Button bounceButton;
    Button fanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        initView();;
        initEven();
    }

    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBarAndBackButton(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("buttommenu");

        bounceButton=(Button)findViewById(R.id.button_menu_bounce);
        fanButton=(Button)findViewById(R.id.button_menu_fan);
    }

    private void initEven(){
        bounceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenuDialog.getInstance().show(getSupportFragmentManager());
            }
        });
        fanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            if(MenuBottomPopupWindown.getInstance(this).isShowing()){
                MenuBottomPopupWindown.getInstance(this).dismiss();
            }else {
                MenuBottomPopupWindown.getInstance(this).showAtLocation(fanButton,Gravity.BOTTOM,0,0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
