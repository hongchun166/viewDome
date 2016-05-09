package com.example.viewdome.ui.activity.pinnedHeader;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.data.pojo.UserPojo;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.adapter.PinnedHeaderAdapter;
import com.example.viewdome.ui.view.alphabet.AlphabetView;
import com.example.viewdome.ui.view.alphabet.ImpOnTouchAssortListener;
import com.example.viewdome.ui.view.pinnedHeader.PinnedHeaderListView;
import com.example.viewdome.utils.CollectionsUtil;
import com.example.viewdome.utils.MStringUtils;

import java.util.ArrayList;
import java.util.List;

public class PinnedHeaderActivity extends BaseNormalActivity {
    Toolbar toolbar;
    TextView titleTextView;

    PinnedHeaderListView pinnedHeaderListView;
    AlphabetView alphabetView;
    PinnedHeaderAdapter mAdapter;
    List<UserPojo> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        initView();
        mData=getTestData();
        mAdapter=new PinnedHeaderAdapter(this,mData);
        pinnedHeaderListView.setAdapter(mAdapter);
    }

    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBarAndBackButton(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("字母分组导航");

        pinnedHeaderListView=(PinnedHeaderListView)findViewById(R.id.pinnedHeaderListView);
        alphabetView=(AlphabetView)findViewById(R.id.alphabetView);
        alphabetView.setOnTouchAssortListener(new ImpOnTouchAssortListener(this,true){
            @Override
            public void onTouchAssortChanged(String s) {
                super.onTouchAssortChanged(s);
                int position = mAdapter.getPositionForSection(s.charAt(0));
                pinnedHeaderListView.setSelection(position);
            }
        });
    }


    private List<UserPojo> getTestData(){
        List<UserPojo> pojoList=new ArrayList<>();
        for (int i=0;i<30;i++){
            pojoList.add(new UserPojo(MStringUtils.getRandomString(6)));
        }
        CollectionsUtil.sortContactPerson(pojoList);
        return pojoList;
    }
}
