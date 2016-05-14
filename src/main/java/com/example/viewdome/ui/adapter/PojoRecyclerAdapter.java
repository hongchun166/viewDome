package com.example.viewdome.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.data.pojo.UserPojo;
import com.example.viewdome.interfacem.OnItemClickLitener;

import java.util.List;
import java.util.Objects;

/**
 * Created by TianHongChun on 2016/5/14.
 */
public class PojoRecyclerAdapter extends RecyclerView.Adapter<PojoRecyclerAdapter.RecyclerViewHodler> {
    private Context context;
    private List<UserPojo> mData;
    private OnItemClickLitener onItemClickLitener;

    public PojoRecyclerAdapter(Context context, List<UserPojo> data){
            this.context=context;
            this.mData=data;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public UserPojo getItem(int position){
            if(position<0||position>mData.size()){
                    return null;
            }
            return mData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(R.layout.item_pojo,parent,false);
            RecyclerViewHodler viewHodler=new RecyclerViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHodler holder,  int position) {
        UserPojo userPojo=mData.get(position);
        if(userPojo!=null){
            holder.getNameTextView().setText(userPojo.getName());
        }
        if(onItemClickLitener!=null){
                holder.getItemRootView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos=holder.getLayoutPosition();
                        onItemClickLitener.onItemClick(holder.getItemRootView(),pos);
                    }
                });
            holder.getItemRootView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickLitener.onItemLongClick(holder.getItemRootView(),pos);
                    return true;
                }
            });
        }

    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }


    public class RecyclerViewHodler extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private View itemRootView;

        public RecyclerViewHodler(View view){
                super(view);
                nameTextView=(TextView) view.findViewById(R.id.textview_name);
                itemRootView=view.findViewById(R.id.rootview_item);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public void setNameTextView(TextView nameTextView) {
            this.nameTextView = nameTextView;
        }

        public View getItemRootView() {
            return itemRootView;
        }

        public void setItemRootView(View itemRootView) {
            this.itemRootView = itemRootView;
        }
    }
}
