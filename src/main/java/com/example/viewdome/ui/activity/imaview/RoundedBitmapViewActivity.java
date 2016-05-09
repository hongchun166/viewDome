package com.example.viewdome.ui.activity.imaview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.view.imagerview.MRoundedBitmapView;

public class RoundedBitmapViewActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    MRoundedBitmapView mRoundedBitmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_bitmap_view);
        initView();
    }

    private void initView(){
            toolbar=(Toolbar)findViewById(R.id.toolbar);
            titleTextView=(TextView)findViewById(R.id.textview_title);
            initToolBarAndBackButton(toolbar);
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText("圆角图片");

            mRoundedBitmapView=(MRoundedBitmapView)findViewById(R.id.mroundedBitmapView);

    }

}
