package com.example.viewdome.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.viewdome.R;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class LoadDialog extends BaseDialog {

    TextView textView;

    ProgressBar progressBar;

    String titleName="处理中...";

    private static LoadDialog loadDiaog;
    public static LoadDialog getInstance(){
        if(loadDiaog==null){
            loadDiaog=new LoadDialog();
        }
        return loadDiaog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view=inflater.inflate(R.layout.dialog_load_layout,container,false);
        textView=(TextView)view.findViewById(R.id.textView);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);

        textView.setText(titleName);

        return view;
    }

    public void show(FragmentManager fragmentManager){
        super.show(fragmentManager,"LoadDialog");
    }
    public LoadDialog setTitleName(String titleName) {
        this.titleName = titleName;
        return loadDiaog;
    }
}
