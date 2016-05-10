package com.example.viewdome.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewdome.R;

/**
 * Created by TianHongChun on 2016/5/10.
 */
public class TwoFragment extends BaseFragment {

    private static TwoFragment twoFragment;

    public static TwoFragment getInstance(){
        if(twoFragment==null){
            synchronized (TwoFragment.class){
                if(twoFragment==null){
                    twoFragment=new TwoFragment();
                }
            }
        }
        return twoFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        return view;
    }
}
