package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/7/29.
 * contact：weileng143@163.com
 *
 * @description  附近的店
 */

public class NearbyRespBean{
//    {"data":[{"guid":"3cb5ec99-1b07-4065-903e-4b86b4abde96","title":
//        "刘刚口腔诊所","content":"http://aclmmanage.jshec.cn/ImgUpload/kindeditor/image/20161230/20161230144135_7544.jpg",
//                "keywords":"13725150568","source":"113.328859,23.144347","mobile":"","stars":"4.6","address":"中山大道西101号",
//            "area":"广东省广州市天河区","officetel":"","isbound":0,"range":12160.18,"pointx":"113.328859","pointy":"23.144347"}]}

    public List<NearbyBeanList> data;

    public List<NearbyBeanList> getData() {
        return data;
    }

    public void setData(List<NearbyBeanList> data) {
        this.data = data;
    }

    public class NearbyBeanList {
        public String guid;
        public String title;
        public String content;
        public String keywords;
        public String source;
        public String mobile;
        public String stars;
        public String address;
        public String area;
        public String officetel;
        public int isbound;
        public double range;
        public String pointx;
        public String pointy;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getOfficetel() {
            return officetel;
        }

        public void setOfficetel(String officetel) {
            this.officetel = officetel;
        }

        public int getIsbound() {
            return isbound;
        }

        public void setIsbound(int isbound) {
            this.isbound = isbound;
        }

        public double getRange() {
            return range;
        }

        public void setRange(double range) {
            this.range = range;
        }

        public void setRange(int range) {
            this.range = range;
        }

        public String getPointx() {
            return pointx;
        }

        public void setPointx(String pointx) {
            this.pointx = pointx;
        }

        public String getPointy() {
            return pointy;
        }

        public void setPointy(String pointy) {
            this.pointy = pointy;
        }

        @Override
        public String toString() {
            return "NearbyBeanList{" +
                    "guid='" + guid + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", source='" + source + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", stars='" + stars + '\'' +
                    ", address='" + address + '\'' +
                    ", area='" + area + '\'' +
                    ", officetel='" + officetel + '\'' +
                    ", isbound=" + isbound +
                    ", range=" + range +
                    ", pointx='" + pointx + '\'' +
                    ", pointy='" + pointy + '\'' +
                    '}';
        }
    }



}
