package com.lovetoothalliance.app.net.bean.response;

/**
 * Author:lt
 * time:2017/8/7.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HomeProductRespBean {


    /**
     * data : {"Guid":"b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377","Name":"我要种牙","OriginalImge":"","ThumbImage":"","SmallImage":"","RepertoryNumber":"","Weight":1000,"RepertoryCount":1,"UnitName":"个","RepertoryAlertCount":1,"PresentScore":0,"PresentRankScore":0,"SocreIntegral":0,"LimitBuyCount":0,"CostPrice":130,"MarketPrice":180,"ShopPrice":170,"Brief":"","Memo":"","Detail":"","ClickCount":0,"CollectCount":0,"BuyCount":0,"CommentCount":0,"ReferCount":0,"IsSaled":1,"IsBest":1,"IsNew":1,"IsHot":1,"IsRecommend":1,"IsReal":1,"IsOnlySaled":1,"Title":"","Description":"","Keywords":"","OrderID":101,"SupplierGuid":"eecb196e-2297-4647-a374-cb9d1228e287","BrandGuid":"00000000-0000-0000-0000-000000000000","BrandName":"","ProductCategoryID":12,"ProductTypeGuid":"00000000-0000-0000-0000-000000000000","CreateUser":"admin","CreateTime":"2017-08-07 10:01:38","ModifyUser":"admin","ModifyTime":"2017-08-07 10:01:38","IsDeleted":0,"SaleNumber":0,"IsDownLoad":0,"IsAudit":1,"IsRight":1,"AgentGroup":"","PushMoney":3,"SupplierLoginID":"yidisha","SupplierCostPrice":130,"SupplierRepertoryCount":0,"SupplierProvidePrice":130,"RecommendedPrice":130,"Supply":0,"AccumulateSupply":0,"SupplierInventory":0,"IsSupplierProduct":1,"ModifyEditState":0,"ModifyDeleteState":0,"ActiveImage":"","CId":-1,"MobileDetail":""}
     */

    public DataBean data;


    public static class DataBean {
        /**
         * Guid : b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377
         * Name : 我要种牙
         * OriginalImge :
         * ThumbImage :
         * SmallImage :
         * RepertoryNumber :
         * Weight : 1000.0
         * RepertoryCount : 1
         * UnitName : 个
         * RepertoryAlertCount : 1
         * PresentScore : 0
         * PresentRankScore : 0
         * SocreIntegral : 0
         * LimitBuyCount : 0
         * CostPrice : 130.0
         * MarketPrice : 180.0
         * ShopPrice : 170.0
         * Brief :
         * Memo :
         * Detail :
         * ClickCount : 0
         * CollectCount : 0
         * BuyCount : 0
         * CommentCount : 0
         * ReferCount : 0
         * IsSaled : 1
         * IsBest : 1
         * IsNew : 1
         * IsHot : 1
         * IsRecommend : 1
         * IsReal : 1
         * IsOnlySaled : 1
         * Title :
         * Description :
         * Keywords :
         * OrderID : 101
         * SupplierGuid : eecb196e-2297-4647-a374-cb9d1228e287
         * BrandGuid : 00000000-0000-0000-0000-000000000000
         * BrandName :
         * ProductCategoryID : 12
         * ProductTypeGuid : 00000000-0000-0000-0000-000000000000
         * CreateUser : admin
         * CreateTime : 2017-08-07 10:01:38
         * ModifyUser : admin
         * ModifyTime : 2017-08-07 10:01:38
         * IsDeleted : 0
         * SaleNumber : 0
         * IsDownLoad : 0
         * IsAudit : 1
         * IsRight : 1
         * AgentGroup :
         * PushMoney : 3.0
         * SupplierLoginID : yidisha
         * SupplierCostPrice : 130.0
         * SupplierRepertoryCount : 0
         * SupplierProvidePrice : 130.0
         * RecommendedPrice : 130.0
         * Supply : 0
         * AccumulateSupply : 0
         * SupplierInventory : 0
         * IsSupplierProduct : 1
         * ModifyEditState : 0
         * ModifyDeleteState : 0
         * ActiveImage :
         * CId : -1.0
         * MobileDetail :
         */

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
        public int PresentScore;
        public int PresentRankScore;
        public int SocreIntegral;
        public int LimitBuyCount;
        public double CostPrice;
        public double MarketPrice;
        public double ShopPrice;
        public String Brief;
        public String Memo;
        public String Detail;
        public int ClickCount;
        public int CollectCount;
        public int BuyCount;
        public int CommentCount;
        public int ReferCount;
        public int IsSaled;
        public int IsBest;
        public int IsNew;
        public int IsHot;
        public int IsRecommend;
        public int IsReal;
        public int IsOnlySaled;
        public String Title;
        public String Description;
        public String Keywords;
        public int OrderID;
        public String SupplierGuid;
        public String BrandGuid;
        public String BrandName;
        public int ProductCategoryID;
        public String ProductTypeGuid;
        public String CreateUser;
        public String CreateTime;
        public String ModifyUser;
        public String ModifyTime;
        public int IsDeleted;
        public int SaleNumber;
        public int IsDownLoad;
        public int IsAudit;
        public int IsRight;
        public String AgentGroup;
        public double PushMoney;
        public String SupplierLoginID;
        public double SupplierCostPrice;
        public int SupplierRepertoryCount;
        public double SupplierProvidePrice;
        public double RecommendedPrice;
        public int Supply;
        public int AccumulateSupply;
        public int SupplierInventory;
        public int IsSupplierProduct;
        public int ModifyEditState;
        public int ModifyDeleteState;
        public String ActiveImage;
        public double CId;
        public String MobileDetail;


        @Override
        public String toString() {
            return "DataBean{" +
                    "Guid='" + Guid + '\'' +
                    ", Name='" + Name + '\'' +
                    ", OriginalImge='" + OriginalImge + '\'' +
                    ", ThumbImage='" + ThumbImage + '\'' +
                    ", SmallImage='" + SmallImage + '\'' +
                    ", RepertoryNumber='" + RepertoryNumber + '\'' +
                    ", Weight=" + Weight +
                    ", RepertoryCount=" + RepertoryCount +
                    ", UnitName='" + UnitName + '\'' +
                    ", RepertoryAlertCount=" + RepertoryAlertCount +
                    ", PresentScore=" + PresentScore +
                    ", PresentRankScore=" + PresentRankScore +
                    ", SocreIntegral=" + SocreIntegral +
                    ", LimitBuyCount=" + LimitBuyCount +
                    ", CostPrice=" + CostPrice +
                    ", MarketPrice=" + MarketPrice +
                    ", ShopPrice=" + ShopPrice +
                    ", Brief='" + Brief + '\'' +
                    ", Memo='" + Memo + '\'' +
                    ", Detail='" + Detail + '\'' +
                    ", ClickCount=" + ClickCount +
                    ", CollectCount=" + CollectCount +
                    ", BuyCount=" + BuyCount +
                    ", CommentCount=" + CommentCount +
                    ", ReferCount=" + ReferCount +
                    ", IsSaled=" + IsSaled +
                    ", IsBest=" + IsBest +
                    ", IsNew=" + IsNew +
                    ", IsHot=" + IsHot +
                    ", IsRecommend=" + IsRecommend +
                    ", IsReal=" + IsReal +
                    ", IsOnlySaled=" + IsOnlySaled +
                    ", Title='" + Title + '\'' +
                    ", Description='" + Description + '\'' +
                    ", Keywords='" + Keywords + '\'' +
                    ", OrderID=" + OrderID +
                    ", SupplierGuid='" + SupplierGuid + '\'' +
                    ", BrandGuid='" + BrandGuid + '\'' +
                    ", BrandName='" + BrandName + '\'' +
                    ", ProductCategoryID=" + ProductCategoryID +
                    ", ProductTypeGuid='" + ProductTypeGuid + '\'' +
                    ", CreateUser='" + CreateUser + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", ModifyUser='" + ModifyUser + '\'' +
                    ", ModifyTime='" + ModifyTime + '\'' +
                    ", IsDeleted=" + IsDeleted +
                    ", SaleNumber=" + SaleNumber +
                    ", IsDownLoad=" + IsDownLoad +
                    ", IsAudit=" + IsAudit +
                    ", IsRight=" + IsRight +
                    ", AgentGroup='" + AgentGroup + '\'' +
                    ", PushMoney=" + PushMoney +
                    ", SupplierLoginID='" + SupplierLoginID + '\'' +
                    ", SupplierCostPrice=" + SupplierCostPrice +
                    ", SupplierRepertoryCount=" + SupplierRepertoryCount +
                    ", SupplierProvidePrice=" + SupplierProvidePrice +
                    ", RecommendedPrice=" + RecommendedPrice +
                    ", Supply=" + Supply +
                    ", AccumulateSupply=" + AccumulateSupply +
                    ", SupplierInventory=" + SupplierInventory +
                    ", IsSupplierProduct=" + IsSupplierProduct +
                    ", ModifyEditState=" + ModifyEditState +
                    ", ModifyDeleteState=" + ModifyDeleteState +
                    ", ActiveImage='" + ActiveImage + '\'' +
                    ", CId=" + CId +
                    ", MobileDetail='" + MobileDetail + '\'' +
                    '}';
        }
    }

}
