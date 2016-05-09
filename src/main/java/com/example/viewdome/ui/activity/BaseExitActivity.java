package com.example.viewdome.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.viewdome.application.MyApplication;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class BaseExitActivity extends BaseActivity {

    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                if((System.currentTimeMillis()-exitTime) > 2000){
                    Toast.makeText(getApplicationContext(),"再次点击退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    MyApplication.getInstance().exit();
                }
                return true;
            }
        return super.onKeyDown(keyCode, event);
    }

}
