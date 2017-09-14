package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/10.
 * contact：weileng143@163.com
 *
 * @description
 */

public class IntegralRespBean {


    public List<DataBean> data;

    public static class DataBean {
        /**
         * rownum : 1
         * Guid : 6f53011b-1c0f-45cb-8d15-470d07fc268b
         * Name : 黑人牙膏
         * OriginalImge : /ImgUpload/20161122194806636.jpg
         * ThumbImage :
         * SmallImage :
         * RepertoryNumber : y02389427
         * Weight : 0.0
         * RepertoryCount : 100
         * UnitName : 支
         * RepertoryAlertCount : 1
         * ExchangeScore : 1
         * Brief : 黑人牙膏，黑牙刷了变白牙的牙膏
         * Memo : fsdfgsdfsf
         *  "Detail": "<img alt="" src="/ImgUpload/kindeditor/image/20160816/20160816111403_0392.jpg" />",
         * CommentCount : 0
         * IsSaled : 1
         * IsBest : 0
         * IsNew : 1
         * IsHot : 0
         * IsRecommend : 0
         * IsReal : 1
         * Title :
         * Description :
         * Keywords :
         * OrderID : 1
         * ScoreProductCategoryID : 1
         * CreateUser : admin
         * CreateTime : 2017-08-10 16:33:39
         * ModifyUser : admin
         * ModifyTime : 2017-08-10 16:40:51
         * IsDeleted : 0
         * SaleNumber : 0
         * IsDownload : 0
         */

        public int rownum;
        public String Guid;
        public String Name;
        public String OriginalImge;
        public String ThumbImage;
        public String SmallImage;
        public String RepertoryNumber;
        public double Weight;
        public int RepertoryCount;
        public String UnitName;
        public int RepertoryAlertCount;
        public int ExchangeScore;
        public String Brief;
        public String Memo;
        public String Detail;
        public int CommentCount;
        public int IsSaled;
        public int IsBest;
        public int IsNew;
        public int IsHot;
        public int IsRecommend;
        public int IsReal;
        public String Title;
        public String Description;
        public String Keywords;
        public int OrderID;
        public int ScoreProductCategoryID;
        public String CreateUser;
        public String CreateTime;
        public String ModifyUser;
        public String ModifyTime;
        public int IsDeleted;
        public int SaleNumber;
        public int IsDownload;

        @Override
        public String toString() {
            return "DataBean{" +
                    "rownum=" + rownum +
                    ", Guid='" + Guid + '\'' +
                    ", Name='" + Name + '\'' +
                    ", OriginalImge='" + OriginalImge + '\'' +
                    ", ThumbImage='" + ThumbImage + '\'' +
                    ", SmallImage='" + SmallImage + '\'' +
                    ", RepertoryNumber='" + RepertoryNumber + '\'' +
                    ", Weight=" + Weight +
                    ", RepertoryCount=" + RepertoryCount +
                    ", UnitName='" + UnitName + '\'' +
                    ", RepertoryAlertCount=" + RepertoryAlertCount +
                    ", ExchangeScore=" + ExchangeScore +
                    ", Brief='" + Brief + '\'' +
                    ", Memo='" + Memo + '\'' +
                    ", Detail='" + Detail + '\'' +
                    ", CommentCount=" + CommentCount +
                    ", IsSaled=" + IsSaled +
                    ", IsBest=" + IsBest +
                    ", IsNew=" + IsNew +
                    ", IsHot=" + IsHot +
                    ", IsRecommend=" + IsRecommend +
                    ", IsReal=" + IsReal +
                    ", Title='" + Title + '\'' +
                    ", Description='" + Description + '\'' +
                    ", Keywords='" + Keywords + '\'' +
                    ", OrderID=" + OrderID +
                    ", ScoreProductCategoryID=" + ScoreProductCategoryID +
                    ", CreateUser='" + CreateUser + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", ModifyUser='" + ModifyUser + '\'' +
                    ", ModifyTime='" + ModifyTime + '\'' +
                    ", IsDeleted=" + IsDeleted +
                    ", SaleNumber=" + SaleNumber +
                    ", IsDownload=" + IsDownload +
                    '}';
        }
    }
}
