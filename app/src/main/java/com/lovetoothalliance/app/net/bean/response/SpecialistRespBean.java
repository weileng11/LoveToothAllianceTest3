package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/9.
 * contact：weileng143@163.com
 *
 * @description
 */

public class SpecialistRespBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 17b706d9-eeba-41be-85f8-8483bb7bf700
         * name : 伊帝沙纯棉小脚休闲裤
         * Brief :
         * shopprice : 138.0
         * stars :
         * OriginalImge : http://www.yidisha.com.cn/ImgUpload/20161109161030271.jpg
         * PayCount : 0
         * ShopName : 刘刚口腔诊所
         * ShopGuid : 3cb5ec99-1b07-4065-903e-4b86b4abde96
         * Source : 113.328859,23.144347
         * Keywords : 13725150568
         * Description :
         * pointx : 113.328859
         * pointy : 23.144347
         */

        public String id;
        public String name;
        public String Brief;
        public double shopprice;
        public String stars;
        public String OriginalImge;
        public int PayCount;
        public String ShopName;
        public String ShopGuid;
        public String Source;
        public String Keywords;
        public String Description;
        public String pointx;
        public String pointy;
    }
}
