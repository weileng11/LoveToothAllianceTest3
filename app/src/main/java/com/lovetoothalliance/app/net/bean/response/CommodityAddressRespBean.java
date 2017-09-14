package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/15.
 * contact：weileng143@163.com
 *
 * @description
 */

public class CommodityAddressRespBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * Guid : 04fbc1ee-a7b9-4f8c-85e1-4b8819ebb937
         * Name : 儿童
         * Email :
         * Address : 北京市 北京市 东城区 是的
         * Postalcode :
         * Tel :
         * Mobile : 13752465286
         * IsDefault : 1
         * MemLoginID : 13725150568
         * CreateUser : 13725150568
         * CreateTime : 2016-12-29 16:31:05
         * ModifyUser : 13725150568
         * ModifyTime : 2016-12-29 16:31:05
         * IsDeleted : 0
         * code :
         */

        public String Guid;
        public String Name;
        public String Email;
        public String Address;
        public String Postalcode;
        public String Tel;
        public String Mobile;
        public int IsDefault;
        public String MemLoginID;
        public String CreateUser;
        public String CreateTime;
        public String ModifyUser;
        public String ModifyTime;
        public int IsDeleted;
        public String code;

        @Override
        public String toString() {
            return "DataBean{" +
                    "Guid='" + Guid + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Email='" + Email + '\'' +
                    ", Address='" + Address + '\'' +
                    ", Postalcode='" + Postalcode + '\'' +
                    ", Tel='" + Tel + '\'' +
                    ", Mobile='" + Mobile + '\'' +
                    ", IsDefault=" + IsDefault +
                    ", MemLoginID='" + MemLoginID + '\'' +
                    ", CreateUser='" + CreateUser + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", ModifyUser='" + ModifyUser + '\'' +
                    ", ModifyTime='" + ModifyTime + '\'' +
                    ", IsDeleted=" + IsDeleted +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
