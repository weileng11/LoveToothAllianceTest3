package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/7/29.
 * contact：weileng143@163.com
 *
 * @description
 */

public class ProudctRespBean {

    public List<ProudctList> data;

    public List<ProudctList> getData() {
        return data;
    }

    public void setData(List<ProudctList> data) {
        this.data = data;
    }

    public class ProudctList {
        private int rownum;
        private String Guid;
        private String Name;
        private String OriginalImge;
        private String ThumbImage;
        private String SmallImage;
        private String RepertoryNumber;
        private double Weight;
        private String UnitName;
        private int RepertoryAlertCount;
        private int PresentScore;
        private int PresentRankScore;
        private int SocreIntegral;
        private int LimitBuyCount;
        private double MarketPrice;
        private double ShopPrice;
        private String Brief;
        private String Detail;
        private int ClickCount;
        private int CollectCount;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        private int BuyCount;
        private int CommentCount;
        private int ReferCount;
        private String ModifyTime;
        private int IsSaled;
        private int IsBest;
        private int IsNew;
        private int IsHot;
        private int IsReal;
        private int IsOnlySaled;
        private String Title;
        private String Description;
        private String Keywords;
        private int OrderID;
        private String BrandName;
        private int ProductCategoryID;
        private int SaleNumber;
        private String ActiveImage;
        private String SupplierLoginID;
        private String BaskOrderLogCount;
        private String AgentID;
        private int IsRecommend;
        private int T;
        private String MobileDetail;
        private String BrandGuid;
        private String SupplierName;
        private String SupplierAddress;
        private String SupplierContact;
        private String SupplierTel;
        private String IsLimited;
        private String IsLowPrice;
        private String ProductProp;
        private String CategoryName;

        public int getRownum() {
            return rownum;
        }

        public void setRownum(int rownum) {
            this.rownum = rownum;
        }

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String guid) {
            Guid = guid;
        }

        public String getOriginalImge() {
            return OriginalImge;
        }

        public void setOriginalImge(String originalImge) {
            OriginalImge = originalImge;
        }

        public String getThumbImage() {
            return ThumbImage;
        }

        public void setThumbImage(String thumbImage) {
            ThumbImage = thumbImage;
        }

        public String getSmallImage() {
            return SmallImage;
        }

        public void setSmallImage(String smallImage) {
            SmallImage = smallImage;
        }

        public String getRepertoryNumber() {
            return RepertoryNumber;
        }

        public void setRepertoryNumber(String repertoryNumber) {
            RepertoryNumber = repertoryNumber;
        }

        public double getWeight() {
            return Weight;
        }

        public void setWeight(double weight) {
            Weight = weight;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String unitName) {
            UnitName = unitName;
        }

        public int getRepertoryAlertCount() {
            return RepertoryAlertCount;
        }

        public void setRepertoryAlertCount(int repertoryAlertCount) {
            RepertoryAlertCount = repertoryAlertCount;
        }

        public int getPresentScore() {
            return PresentScore;
        }

        public void setPresentScore(int presentScore) {
            PresentScore = presentScore;
        }

        public int getPresentRankScore() {
            return PresentRankScore;
        }

        public void setPresentRankScore(int presentRankScore) {
            PresentRankScore = presentRankScore;
        }

        public int getSocreIntegral() {
            return SocreIntegral;
        }

        public void setSocreIntegral(int socreIntegral) {
            SocreIntegral = socreIntegral;
        }

        public int getLimitBuyCount() {
            return LimitBuyCount;
        }

        public void setLimitBuyCount(int limitBuyCount) {
            LimitBuyCount = limitBuyCount;
        }

        public double getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            MarketPrice = marketPrice;
        }

        public double getShopPrice() {
            return ShopPrice;
        }

        public void setShopPrice(double shopPrice) {
            ShopPrice = shopPrice;
        }

        public String getBrief() {
            return Brief;
        }

        public void setBrief(String brief) {
            Brief = brief;
        }

        public String getDetail() {
            return Detail;
        }

        public void setDetail(String detail) {
            Detail = detail;
        }

        public int getClickCount() {
            return ClickCount;
        }

        public void setClickCount(int clickCount) {
            ClickCount = clickCount;
        }

        public int getCollectCount() {
            return CollectCount;
        }

        public void setCollectCount(int collectCount) {
            CollectCount = collectCount;
        }

        public int getBuyCount() {
            return BuyCount;
        }

        public void setBuyCount(int buyCount) {
            BuyCount = buyCount;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int commentCount) {
            CommentCount = commentCount;
        }

        public int getReferCount() {
            return ReferCount;
        }

        public void setReferCount(int referCount) {
            ReferCount = referCount;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public void setModifyTime(String modifyTime) {
            ModifyTime = modifyTime;
        }

        public int getIsSaled() {
            return IsSaled;
        }

        public void setIsSaled(int isSaled) {
            IsSaled = isSaled;
        }

        public int getIsBest() {
            return IsBest;
        }

        public void setIsBest(int isBest) {
            IsBest = isBest;
        }

        public int getIsNew() {
            return IsNew;
        }

        public void setIsNew(int isNew) {
            IsNew = isNew;
        }

        public int getIsHot() {
            return IsHot;
        }

        public void setIsHot(int isHot) {
            IsHot = isHot;
        }

        public int getIsReal() {
            return IsReal;
        }

        public void setIsReal(int isReal) {
            IsReal = isReal;
        }

        public int getIsOnlySaled() {
            return IsOnlySaled;
        }

        public void setIsOnlySaled(int isOnlySaled) {
            IsOnlySaled = isOnlySaled;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getKeywords() {
            return Keywords;
        }

        public void setKeywords(String keywords) {
            Keywords = keywords;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int orderID) {
            OrderID = orderID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String brandName) {
            BrandName = brandName;
        }

        public int getProductCategoryID() {
            return ProductCategoryID;
        }

        public void setProductCategoryID(int productCategoryID) {
            ProductCategoryID = productCategoryID;
        }

        public int getSaleNumber() {
            return SaleNumber;
        }

        public void setSaleNumber(int saleNumber) {
            SaleNumber = saleNumber;
        }

        public String getActiveImage() {
            return ActiveImage;
        }

        public void setActiveImage(String activeImage) {
            ActiveImage = activeImage;
        }

        public String getSupplierLoginID() {
            return SupplierLoginID;
        }

        public void setSupplierLoginID(String supplierLoginID) {
            SupplierLoginID = supplierLoginID;
        }

        public String getBaskOrderLogCount() {
            return BaskOrderLogCount;
        }

        public void setBaskOrderLogCount(String baskOrderLogCount) {
            BaskOrderLogCount = baskOrderLogCount;
        }

        public String getAgentID() {
            return AgentID;
        }

        public void setAgentID(String agentID) {
            AgentID = agentID;
        }

        public int getIsRecommend() {
            return IsRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            IsRecommend = isRecommend;
        }

        public int getT() {
            return T;
        }

        public void setT(int t) {
            T = t;
        }

        public String getMobileDetail() {
            return MobileDetail;
        }

        public void setMobileDetail(String mobileDetail) {
            MobileDetail = mobileDetail;
        }

        public String getBrandGuid() {
            return BrandGuid;
        }

        public void setBrandGuid(String brandGuid) {
            BrandGuid = brandGuid;
        }

        public String getSupplierName() {
            return SupplierName;
        }

        public void setSupplierName(String supplierName) {
            SupplierName = supplierName;
        }

        public String getSupplierAddress() {
            return SupplierAddress;
        }

        public void setSupplierAddress(String supplierAddress) {
            SupplierAddress = supplierAddress;
        }

        public String getSupplierContact() {
            return SupplierContact;
        }

        public void setSupplierContact(String supplierContact) {
            SupplierContact = supplierContact;
        }

        public String getSupplierTel() {
            return SupplierTel;
        }

        public void setSupplierTel(String supplierTel) {
            SupplierTel = supplierTel;
        }

        public String getIsLimited() {
            return IsLimited;
        }

        public void setIsLimited(String isLimited) {
            IsLimited = isLimited;
        }

        public String getIsLowPrice() {
            return IsLowPrice;
        }

        public void setIsLowPrice(String isLowPrice) {
            IsLowPrice = isLowPrice;
        }

        public String getProductProp() {
            return ProductProp;
        }

        public void setProductProp(String productProp) {
            ProductProp = productProp;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }
    }


}
