package com.example.viewdome.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.bottommenu.BottomMenuActivity;
import com.example.viewdome.ui.activity.imaview.RoundedBitmapViewActivity;
import com.example.viewdome.ui.activity.pinnedHeader.PinnedHeaderActivity;
import com.example.viewdome.ui.activity.pullRefresh.PullRefreshTextViewActivity;
import com.example.viewdome.ui.activity.pullRefresh.PullrefreshListviewActivity;
import com.example.viewdome.ui.activity.swiper.SwiperActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class MainFragment extends BaseFragment {

    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    private static MainFragment mainFragment;

    public static MainFragment getInstance(){
        if(mainFragment==null){
            synchronized (MainFragment.class){
                if(mainFragment==null){
                    mainFragment=new MainFragment();
                }
            }
        }
        return mainFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        initView(view);
        listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getTestData()));

        return view;
    }
    private void initView(View view){
        listView=(ListView)view.findViewById(R.id.listview);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                closeRefresh();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent=new Intent(context, PullrefreshListviewActivity.class);
                        startActivity(intent);

                        break;
                    }
                    case 1:{
                        Intent intent=new Intent(context, PullRefreshTextViewActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        Intent intent=new Intent(context, PinnedHeaderActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        Intent intent=new Intent(context, SwiperActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Intent intent=new Intent(context, RoundedBitmapViewActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        Intent intent=new Intent(context, BottomMenuActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }


    private void closeRefresh(){
        if(swipeRefreshLayout==null){
            return;
        }
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
    private void openRefresh(){
        if(swipeRefreshLayout==null){
            return;
        }
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        }, 100);
    }


    private List<String> getTestData(){
        List<String> stringList=new ArrayList<>();
        stringList.add("下拉刷新:PullRefreshLayout.PullRefreshListView");
        stringList.add("下拉刷新:PullRefreshLayout.PullRefreshTextView");
        stringList.add("字母分组,导航;PinnedHeaderListView.AlphabetView");
        stringList.add("滑动删除:SwipeListView.SwipeItemView");
        stringList.add("圆角图片:MRoundedBitmapView");
        stringList.add("底部menu菜单:bottommenu");
        for (int i=0;i<20;i++){
            stringList.add("test:"+i);
        }
        return stringList;
    }
}
