package com.example.viewdome.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.dialog.LoadDialog;
import com.example.viewdome.ui.dialog.MenuMainPopupWindown;
import com.example.viewdome.ui.fragment.MainFragment;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

public class MainActivity extends BaseExitActivity {


    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initView();

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout,MainFragment.getInstance(),"MainFragment").commit();

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBar(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("主页");

        menuButton=(Button)findViewById(R.id.button_menu);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        // DrawerLayout 与menu绑定
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuMainPopupWindown popupWindown=new MenuMainPopupWindown(v.getContext());
                popupWindown.showAsDropDown(v);

            }
        });
    }
}
