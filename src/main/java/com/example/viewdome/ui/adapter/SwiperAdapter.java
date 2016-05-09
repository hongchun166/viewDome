package com.example.viewdome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewdome.R;
import com.example.viewdome.ui.view.swipe.SwipeItemView;

import java.util.List;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class SwiperAdapter extends BaseAdapter {

    private final int ITEM_TYPE_ONE=0;
    private final int ITEM_TYPE_TEO=1;

    Context context;
    LayoutInflater inflater;
    List<String> mData;

    public SwiperAdapter(Context context,List<String> data){
            this.context=context;
        this.mData=data;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==3){
            return ITEM_TYPE_TEO;
        }else {
            return ITEM_TYPE_ONE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       final ViewHodler hodler;
        SwipeItemView slideView = (SwipeItemView) convertView;

        if(slideView==null){
                View contentView=inflater.inflate(R.layout.item_swiper_content,null);
                slideView=new SwipeItemView(context);
                slideView.setContentView(contentView);
            if(getItemViewType(position)==ITEM_TYPE_TEO){
                View hideView=inflater.inflate(R.layout.item_swiper_hide2,null);
                slideView.setHideView(hideView);
            }else {
                View hideView=inflater.inflate(R.layout.item_swiper_hide,null);
                slideView.setHideView(hideView);
            }
            hodler = new ViewHodler(slideView);
            slideView.setTag(hodler);
        }else {
            hodler=(ViewHodler)convertView.getTag();
        }

        final   String string=mData.get(position);
        hodler.getNameTextView().setText(string);
        hodler.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hodler.getSwipeItemView1().shrink();
                Toast.makeText(context, "删除:" +string, Toast.LENGTH_SHORT).show();
            }
        });
        hodler.getCollectButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hodler.getSwipeItemView1().shrink();
                Toast.makeText(context, "收藏:" + string, Toast.LENGTH_SHORT).show();
            }
        });

        if(hodler.getTopButton()!=null){
            hodler.getTopButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hodler.getSwipeItemView1().shrink();
                    Toast.makeText(context,"置顶:"+string,Toast.LENGTH_SHORT).show();
                }
            });

        }

        return slideView;
    }

    public class ViewHodler{
        // item view
        private SwipeItemView swipeItemView1;

        // content view
        private TextView nameTextView;
        private TextView contentTextView;

        //hide chid view
        private TextView deleteButton;
        private TextView collectButton;
        private TextView topButton;


        public ViewHodler(View view){
            this.swipeItemView1=(SwipeItemView)view;
            nameTextView=(TextView)view.findViewById(R.id.textview_name);
            deleteButton=(TextView)view.findViewById(R.id.button_delete);
            collectButton=(TextView)view.findViewById(R.id.button_collect);
            topButton=(TextView)view.findViewById(R.id.button_top);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public void setNameTextView(TextView nameTextView) {
            this.nameTextView = nameTextView;
        }

        public SwipeItemView getSwipeItemView1() {
            return swipeItemView1;
        }

        public void setSwipeItemView1(SwipeItemView swipeItemView1) {
            this.swipeItemView1 = swipeItemView1;
        }

        public TextView getContentTextView() {
            return contentTextView;
        }

        public void setContentTextView(TextView contentTextView) {
            this.contentTextView = contentTextView;
        }

        public TextView getDeleteButton() {
            return deleteButton;
        }

        public void setDeleteButton(TextView deleteButton) {
            this.deleteButton = deleteButton;
        }

        public TextView getCollectButton() {
            return collectButton;
        }

        public void setCollectButton(TextView collectButton) {
            this.collectButton = collectButton;
        }

        public TextView getTopButton() {
            return topButton;
        }

        public void setTopButton(TextView topButton) {
            this.topButton = topButton;
        }
    }
}
