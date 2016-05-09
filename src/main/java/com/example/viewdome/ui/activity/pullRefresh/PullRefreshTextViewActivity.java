package com.example.viewdome.ui.activity.pullRefresh;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.view.pull.PullRefreshLayout;
import com.example.viewdome.ui.view.pull.index.PullRefreshTextView;

public class PullRefreshTextViewActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    PullRefreshLayout pullRefreshLayout;
    PullRefreshTextView pullRefreshTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh_text_view);
        initView();
    }

    private void initView(){
            toolbar=(Toolbar)findViewById(R.id.toolbar);
            titleTextView=(TextView)findViewById(R.id.textview_title);
            initToolBarAndBackButton(toolbar);
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText("pullRefreshTextView");

        pullRefreshLayout=(PullRefreshLayout)findViewById(R.id.pullRefreshLayout);
        pullRefreshTextView=(PullRefreshTextView)findViewById(R.id.pullRefreshTextView);

        pullRefreshLayout.setOnPullToRefreshListener(new PullRefreshLayout.OnPullToRefreshListener() {
            @Override
            public void onRefreshDown(PullRefreshLayout pullRefreshLayout) {
                pullRefreshTextView.setText("执行了下拉刷新");
                closeRefresh();
            }

            @Override
            public void onRefreshUp(PullRefreshLayout pullRefreshLayout) {
                pullRefreshTextView.setText("执行了上啦");
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
}
