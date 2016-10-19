package com.ycx.realmdemo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 李小明 on 16/10/18.
 * 邮箱:287907160@qq.com
 */

public class City extends RealmObject{

    @PrimaryKey
    private String cityId;

    private String name;

    private int count;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
