package com.example.viewdome.data.pojo;

import android.text.TextUtils;
import android.view.animation.PathInterpolator;

import com.example.viewdome.utils.MStringUtils;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class UserPojo {

    private String name;
    private String sortLetter="#";
    private String pinyin;

    public UserPojo(){

    }
    public UserPojo(String name){
        this.name=name;
        this.pinyin = MStringUtils.getPingYin(name);

        if (!TextUtils.isEmpty(pinyin)) {
            String sortString = this.pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                this.sortLetter = sortString.toUpperCase();
            } else {
                this.sortLetter = "#";
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
