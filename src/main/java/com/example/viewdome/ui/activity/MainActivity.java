package com.example.viewdome.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.pinnedHeader.PinnedHeaderActivity;
import com.example.viewdome.ui.adapter.MFragmentPagerAdapter;
import com.example.viewdome.ui.dialog.LoadDialog;
import com.example.viewdome.ui.dialog.MenuRightTopPopupWindown;
import com.example.viewdome.ui.fragment.MainFragment;
import com.example.viewdome.ui.fragment.PojoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseExitActivity {

    Toolbar toolbar;
    TextView titleTextView;
    Button menuButton;
    TabLayout tabLayout;
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    ViewPager viewPager;


    MFragmentPagerAdapter mFragmentPagerAdapter;
    List<Fragment> fragmentList;
    List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEven();
        fragmentList=new ArrayList<>();
        fragmentList.add(MainFragment.getInstance());
        fragmentList.add(PojoFragment.getInstance());

        titles=new ArrayList<>();
        titles.add("main");
        titles.add("two");

        mFragmentPagerAdapter = new MFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(mFragmentPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoadDialog.getInstance().show(getSupportFragmentManager());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadDialog.getInstance().dismiss();
            }
        }, 2000);
    }

    private void initView(){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBar(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("主页");
        menuButton=(Button)findViewById(R.id.button_menu);
        menuButton.setVisibility(View.VISIBLE);
        menuButton.setText("MENU");

        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        // DrawerLayout 与menu绑定
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void initEven(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), PinnedHeaderActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,fab,fab.getTransitionName()).toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuRightTopPopupWindown popupWindown=new MenuRightTopPopupWindown(v.getContext());
                popupWindown.showAsDropDown(v);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
