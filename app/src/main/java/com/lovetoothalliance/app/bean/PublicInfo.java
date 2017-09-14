package com.lovetoothalliance.app.bean;

/**
 * Author:lt
 * time:2017/7/21.
 * contact：weileng143@163.com
 *
 * @description
 */

public class PublicInfo {
    private String name;
    private int ivContent;

    public PublicInfo(String name, int ivContent){
        this.name=name;
        this.ivContent=ivContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIvContent() {
        return ivContent;
    }

    public void setIvContent(int ivContent) {
        this.ivContent = ivContent;
    }
}
