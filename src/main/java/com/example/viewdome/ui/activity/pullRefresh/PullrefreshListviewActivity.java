package com.example.viewdome.ui.activity.pullRefresh;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.view.pull.PullRefreshLayout;
import com.example.viewdome.ui.view.pull.index.PullRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class PullrefreshListviewActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    PullRefreshLayout pullRefreshLayout;
    PullRefreshListView pullRefreshListView;
    List<String> mData;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullrefresh_listview_);
        initView();
        mData=getTestData();
        mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData);
        pullRefreshListView.setAdapter(mAdapter);
    }

    public void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        initToolBarAndBackButton(toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("pullRefreshListView:");

        pullRefreshLayout=(PullRefreshLayout)findViewById(R.id.pullRefreshLayout);
        pullRefreshListView=(PullRefreshListView)findViewById(R.id.pullRefreshListView);


        pullRefreshLayout.setOnPullToRefreshListener(new PullRefreshLayout.OnPullToRefreshListener() {
            @Override
            public void onRefreshDown(PullRefreshLayout pullRefreshLayout) {
                mData.clear();
                mData.addAll(getTestDataRefresh());
                mAdapter.notifyDataSetChanged();
                closeRefresh();
            }

            @Override
            public void onRefreshUp(PullRefreshLayout pullRefreshLayout) {
                mData.addAll(getTestDataLoad());
                mAdapter.notifyDataSetChanged();
                closeRefreshLoad();
            }
        });
    }

    public void closeRefresh(){
        if(pullRefreshLayout!=null){
            pullRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullRefreshLayout.setRefresh(false);
                }
            }, 1000);
        }
    }
    public void openRefresh(){
        if(pullRefreshLayout!=null){
            pullRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    pullRefreshLayout.setRefresh(true);
                }
            });
        }
    }
    public void closeRefreshLoad(){
        if(pullRefreshLayout!=null){
            pullRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullRefreshLayout.setRefreshLoad(false);
                }
            }, 1000);
        }
    }
    public void openRefreshLoad(){
        if(pullRefreshLayout!=null){
            pullRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    pullRefreshLayout.setRefreshLoad(true);
                }
            });
        }
    }

    private List<String> getTestData(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<10;i++){
            stringList.add("item:"+i);
        }
        return stringList;
    }
    private List<String> getTestDataRefresh(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<3;i++){
            stringList.add("item,down:"+i);
        }
        return stringList;
    }
    private List<String> getTestDataLoad(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<3;i++){
            stringList.add("上啦加载:"+i);
        }
        return stringList;
    }
}
