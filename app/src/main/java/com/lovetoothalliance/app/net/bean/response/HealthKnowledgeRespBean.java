package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/11.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HealthKnowledgeRespBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * rownum : 1
         * Guid : b533ac32-b973-4d7b-9af6-ddf825a3bfcd
         * Title : 介绍
         * ArticleCategoryID : 3
         * Publisher : 小唐
         * Source : 13725150568
         * Content : 双方的说法是否斯蒂芬
         * Keywords :
         * Description :
         * IsShow : 1
         * IsAllowComment : 0
         * OrderID : 4
         * IsHot : 0
         * IsRecommend : 0
         * IsHead : 0
         * IsCanConfigure : 0
         * CreateUser : admin
         * CreateTime : 2016-12-28 10:29:43
         * ModifyUser : admin
         * ModifyTime : 2016-12-28 10:36:23
         * IsDeleted : 0
         * ClickCount : 0
         */

        public int rownum;
        public String Guid;
        public String Title;
        public int ArticleCategoryID;
        public String Publisher;
        public String Source;
        public String Content;
        public String Keywords;
        public String Description;
        public int IsShow;
        public int IsAllowComment;
        public int OrderID;
        public int IsHot;
        public int IsRecommend;
        public int IsHead;
        public int IsCanConfigure;
        public String CreateUser;
        public String CreateTime;
        public String ModifyUser;
        public String ModifyTime;
        public int IsDeleted;
        public int ClickCount;
    }
}
