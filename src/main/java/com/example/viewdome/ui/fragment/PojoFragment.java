package com.example.viewdome.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.viewdome.R;
import com.example.viewdome.data.pojo.UserPojo;
import com.example.viewdome.interfacem.OnItemClickLitener;
import com.example.viewdome.ui.adapter.PojoRecyclerAdapter;
import com.example.viewdome.ui.view.recycler.DefaultItemDecoration;
import com.example.viewdome.utils.CollectionsUtil;
import com.example.viewdome.utils.MStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TianHongChun on 2016/5/10.
 */
public class PojoFragment extends BaseFragment {

    private Context context;
    private RecyclerView recyclerView;
    PojoRecyclerAdapter mAdapter;
    List<UserPojo> mData;

    private static PojoFragment pojoFragment;
    public static PojoFragment getInstance(){
        if(pojoFragment ==null){
            synchronized (PojoFragment.class){
                if(pojoFragment ==null){
                    pojoFragment =new PojoFragment();
                }
            }
        }
        return pojoFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        initView(view);
        initEven();
        return view;
    }

    private void initView(View view){
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DefaultItemDecoration(context,LinearLayoutManager.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mData=getTestData();
        mAdapter=new PojoRecyclerAdapter(context,mData);
        recyclerView.setAdapter(mAdapter);
    }
    private void initEven(){
        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                UserPojo userPojo=mAdapter.getItem(position);
                if(userPojo==null){
                    Toast.makeText(context,"==null==",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context,"onItemClick:"+userPojo.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                UserPojo userPojo=mAdapter.getItem(position);
                if(userPojo==null){
                    Toast.makeText(context,"==null==",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context,"onItemLongClick:"+userPojo.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    LinearLayoutManager layoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();
                    int firstVisibleItemPosition=layoutManager.findFirstVisibleItemPosition();

                    if(lastVisibleItemPosition==mAdapter.getItemCount()-1){
                        toast("滚动到底部了");
                    }else if(firstVisibleItemPosition==0){
                        toast("滚动到顶部了");
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }


    private void log(String content){
            Log.i(PojoFragment.class.getName(),""+content);
    }

    private void toast(String content){
        Toast.makeText(context,""+content,Toast.LENGTH_SHORT).show();
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
