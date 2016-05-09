package com.example.viewdome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.viewdome.R;
import com.example.viewdome.data.pojo.UserPojo;
import com.example.viewdome.ui.view.pinnedHeader.PinnedHeaderAdapterListener;

import java.util.List;

/**
 * Created by TianHongChun on 2016/4/11.
 */
public class PinnedHeaderAdapter extends BaseAdapter implements PinnedHeaderAdapterListener {

    private  Context context;
    LayoutInflater inflater;
    private List<UserPojo> pojoList;

    public PinnedHeaderAdapter(Context context, List<UserPojo> contactPersonPojoList){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.pojoList=contactPersonPojoList;
    }

    @Override
    public int getCount() {
        return pojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return (position >= 0 && position < pojoList.size()) ? pojoList.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserPojo userPojo=pojoList.get(position);
        ItemViewHolder itemViewHolder=null;

        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_pinnedheader,null);
            itemViewHolder=new ItemViewHolder(convertView);
            convertView.setTag(itemViewHolder);
        }else {
            itemViewHolder=(ItemViewHolder)convertView.getTag();
        }
        itemViewHolder.getNameTextView().setText(userPojo.getName());

        String firstLetter=userPojo.getSortLetter();
        int section =firstLetter.charAt(0);
        if(position==getPositionForSection(section)){
                itemViewHolder.getGroupTextView().setVisibility(View.VISIBLE);
            itemViewHolder.getGroupTextView().setText(firstLetter);
        }else{
            itemViewHolder.getGroupTextView().setVisibility(View.GONE);
        }
        return convertView;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i <getCount(); i++) {
            String sortStr =pojoList.get(i).getSortLetter();
            if(sortStr.matches("[A-Z]")){
            }else{
                sortStr="#";
            }
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getPinnedHeaderState(int position) {
        if (position < 0) {
            return PINNED_HEADER_GONE;
        }
        UserPojo item = (UserPojo) getItem(position);
        Object object=getItem(position + 1);
        UserPojo itemNext=null;
        if(object!=0){
            itemNext=(UserPojo)object;
        }
        boolean isNextSection =false;
        boolean isSection=false;
        if(null!=itemNext){
            if(position+1== getPositionForSection(itemNext.getSortLetter().charAt(0))){
                isNextSection=true;
            }
        }
        if(position == getPositionForSection(item.getSortLetter().charAt(0))){
            isSection=true;
        }
//        if (!isSection && isNextSection) {
//            return PINNED_HEADER_PUSHED_UP;
//        }
        if (isNextSection) {
            return PINNED_HEADER_PUSHED_UP;
        }
        return PINNED_HEADER_VISIBLE;
    }

    @Override
    public void configurePinnedHeader(View headerView, int firstPosition,int alpha) {
        TextView textView=(TextView)headerView.findViewById(R.id.textview_group);

        UserPojo contactPersonPojo=pojoList.get(firstPosition);
       String firstLetter= contactPersonPojo.getSortLetter();
        // 正则表达式，判断首字母是否是英文字母
        if(firstLetter.matches("[A-Z]")){
        }else{
            firstLetter="#";
        }
        textView.setText(firstLetter);
    }

    @Override
    public View getPinnedHeaderView(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_pinnedheader_group,null);
        view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    public class ItemViewHolder {
        TextView nameTextView;
        TextView groupTextView;

        public ItemViewHolder(View itemView) {
            nameTextView=(TextView)itemView.findViewById(R.id.textview_name);
            groupTextView=(TextView)itemView.findViewById(R.id.textview_group);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public void setNameTextView(TextView nameTextView) {
            this.nameTextView = nameTextView;
        }

        public TextView getGroupTextView() {
            return groupTextView;
        }

        public void setGroupTextView(TextView groupTextView) {
            this.groupTextView = groupTextView;
        }
    }

}
