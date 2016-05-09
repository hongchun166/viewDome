package com.example.viewdome.ui.view.pull.index;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.viewdome.ui.view.pull.Pullable;


/**
 * Created by TianHongChun on 2016/4/16.
 */
public class PullRefreshTextView extends TextView implements Pullable {
    public PullRefreshTextView(Context context) {
        super(context);
    }
    public PullRefreshTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        return true;
    }

    @Override
    public boolean canPullUp() {
        return true;
    }
}
