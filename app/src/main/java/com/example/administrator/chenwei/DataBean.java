package com.example.administrator.chenwei;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public class DataBean {

    /**
     * zt : 有效
     * title : 会议名称
     * name : 陈伟
     * phone : 13911107099
     * ma : 5200055221
     * nums : 2
     */

    private String zt;
    private String title;
    private String name;
    private String phone;
    private String ma;
    private String nums;

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "zt='" + zt + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", ma='" + ma + '\'' +
                ", nums='" + nums + '\'' +
                '}';
    }
}
