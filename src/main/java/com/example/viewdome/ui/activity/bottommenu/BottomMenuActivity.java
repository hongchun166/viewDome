package com.example.viewdome.ui.activity.bottommenu;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.ui.activity.BaseNormalActivity;
import com.example.viewdome.ui.dialog.BottomMenuDialog;

public class BottomMenuActivity extends BaseNormalActivity {

    Toolbar toolbar;
    TextView titleTextView;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        initView();;
        initEven();
    }

    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        titleTextView=(TextView)findViewById(R.id.textview_title);
        initToolBarAndBackButton(toolbar);
        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("buttommenu");

        addButton=(Button)findViewById(R.id.button_add);

    }

    private void initEven(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenuDialog.getInstance().show(getSupportFragmentManager());
            }
        });
    }


}
