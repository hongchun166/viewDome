package com.example.viewdome.ui.activity.swiper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseActivity;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.adapter.SwiperAdapter;
import com.example.viewdome.ui.view.swipe.SwipeListView;
import com.example.viewdome.utils.MStringUtils;

import java.util.ArrayList;
import java.util.List;

public class SwiperActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    SwipeListView swipeListView;

    List<String> mData;
    SwiperAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiper);
        initView();

        mData=getTestData();
        mAdapter=new SwiperAdapter(this,mData);
        swipeListView.setAdapter(mAdapter);

    }
    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBarAndBackButton(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("滑动删除");
        swipeListView=(SwipeListView)findViewById(R.id.swipeListView);


        swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "==onItemClick:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        swipeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "==onItemLongClick:" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    private List<String> getTestData(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<20;i++){
            stringList.add(MStringUtils.getRandomString(5));
        }
        return stringList;
    }

}
