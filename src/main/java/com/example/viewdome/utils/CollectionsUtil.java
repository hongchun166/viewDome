package com.example.viewdome.utils;

import com.example.viewdome.data.pojo.UserPojo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TianHongChun on 2016/4/12.
 */
public class CollectionsUtil {
    private CollectionsUtil(){
    }


    public static   void sortContactPerson(List<UserPojo> list){
        if(list.size()>1){
            Collections.sort(list, new Comparator<UserPojo>() {
                @Override
                public int compare(UserPojo lhs, UserPojo rhs) {
                    return lhs.getPinyin().compareToIgnoreCase(rhs.getPinyin());
                }
            });
        }
    }
}

