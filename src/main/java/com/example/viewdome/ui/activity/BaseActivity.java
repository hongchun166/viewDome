package com.example.viewdome.ui.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.viewdome.application.MyApplication;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


    public void initToolBar(Toolbar mToolBar){
        setSupportActionBar(mToolBar);
        if (getSupportActionBar()!=null){
            //其他操作
            getSupportActionBar().setTitle("");
        }
    }
    /**
     * 初始化toolbar
     * @param mToolBar
     */
    public void initToolBar(Toolbar mToolBar,String title){
        setSupportActionBar(mToolBar);
        if (getSupportActionBar()!=null){
            //其他操作
            getSupportActionBar().setTitle(title);
        }
    }
    /**
     * 初始化带有返回按钮的toolbar
     * @param mToolBar
     */
    public void initToolBarAndBackButton(Toolbar mToolBar ){
        setSupportActionBar(mToolBar);
        if (getSupportActionBar()!=null){
            //开启返回按钮
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
    /**
     * 初始化带有返回按钮的toolbar
     * @param mToolBar
     */
    public void initToolBarAndBackButton(Toolbar mToolBar,String title){
        setSupportActionBar(mToolBar);
        if (getSupportActionBar()!=null){
            //开启返回按钮
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
