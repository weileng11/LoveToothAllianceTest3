package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/17.
 * contact：weileng143@163.com
 *
 * @description  个人信息
 */

public class PresonInformationRespBean {


    public List<DataBean> data;

    public static class DataBean {
        /**
         * Guid : 74310ba4-41e1-4a28-b968-c0d8d5a2e283
         * MemLoginID : 13725150568
         * Email : 13725150568@qq.com
         * Pwd : e10adc3949ba59abbe56e057f20f883e
         * PayPwd : e10adc3949ba59abbe56e057f20f883e
         * Sex : 0
         * Age : 0
         * Birthday : 1990-01-01 00:00:00
         * CreditMoney : 0.0
         * Photo : http://7y2ow0.com1.z0.glb.clouddn.com/apicloud/f0c1781741c0b06f95ba85794b8a2ced.jpg
         * RealName : 如果
         * Area : 广东省广州市天河区
         * Vocation :
         * Address : 中山大道西101号
         * Postalcode :
         * OfficeTel :
         * HomeTel :
         * Mobile :
         * Fax :
         * QQ :
         * Msn : {"Body":"18000000000212\n\n\n88888888\n","Cqyaowu":"18000000000","Cure":"18000000000","Illness":"18000000000","Medicine":"18000000000","Nationality":"国籍","Outpatient":"18000000000","Pregnancy":"18000000000","Slice":"18000000000","Smoke":"18000000000","Special":"18000000000","Time":"18000000000","Toothwei":"18000000000"}
         * CardType :
         * CardNum :
         * WebSite :
         * Question :
         * Answer :
         * RegDate : 2016-11-21 11:36:43
         * LastLoginDate : 2017-08-18 15:07:01
         * LastLoginIP : 183.63.173.122
         * LoginTime : 723
         * AdvancePayment : 0.0
         * Score : 0
         * RankScore : 0
         * LockAdvancePayment : 0.0
         * LockSocre : 0
         * CostMoney : 0.0
         * MemberRankGuid : 05c29601-930f-4f35-9d36-3c9839914276
         * TradeCount : 0
         * IsForbid : 0
         * CreateUser : Admin
         * CreateTime : 2016-11-21 11:36:43
         * ModifyUser : Admin
         * ModifyTime : 2016-12-26 16:40:07
         * IsDeleted : 0
         * IsAgentID : 1
         * AgentRankScore : 0
         * AgentValidity : 2020-07-16 00:00:00
         * PaymentType : 0
         * CommendCondition : 0
         * AreaCode : 019001005
         * WW :
         * AppKey : 2c07694b782c43859defb6b9bbed4c7d
         * Tshou : 0
         * TEmail : 0
         * IsSetTemplates : 1
         */

        public String Guid;
        public String MemLoginID;
        public String Email;
        public String Pwd;
        public String PayPwd;
        public int Sex;
        public int Age;
        public String Birthday;
        public double CreditMoney;
        public String Photo;
        public String RealName;
        public String Area;
        public String Vocation;
        public String Address;
        public String Postalcode;
        public String OfficeTel;
        public String HomeTel;
        public String Mobile;
        public String Fax;
        public String QQ;
        public String Msn;
        public String CardType;
        public String CardNum;
        public String WebSite;
        public String Question;
        public String Answer;
        public String RegDate;
        public String LastLoginDate;
        public String LastLoginIP;
        public int LoginTime;
        public double AdvancePayment;
        public int Score;
        public int RankScore;
        public double LockAdvancePayment;
        public int LockSocre;
        public double CostMoney;
        public String MemberRankGuid;
        public int TradeCount;
        public int IsForbid;
        public String CreateUser;
        public String CreateTime;
        public String ModifyUser;
        public String ModifyTime;
        public int IsDeleted;
        public int IsAgentID;
        public int AgentRankScore;
        public String AgentValidity;
        public int PaymentType;
        public int CommendCondition;
        public String AreaCode;
        public String WW;
        public String AppKey;
        public int Tshou;
        public int TEmail;
        public int IsSetTemplates;

        @Override
        public String toString() {
            return "DataBean{" +
                    "Guid='" + Guid + '\'' +
                    ", MemLoginID='" + MemLoginID + '\'' +
                    ", Email='" + Email + '\'' +
                    ", Pwd='" + Pwd + '\'' +
                    ", PayPwd='" + PayPwd + '\'' +
                    ", Sex=" + Sex +
                    ", Age=" + Age +
                    ", Birthday='" + Birthday + '\'' +
                    ", CreditMoney=" + CreditMoney +
                    ", Photo='" + Photo + '\'' +
                    ", RealName='" + RealName + '\'' +
                    ", Area='" + Area + '\'' +
                    ", Vocation='" + Vocation + '\'' +
                    ", Address='" + Address + '\'' +
                    ", Postalcode='" + Postalcode + '\'' +
                    ", OfficeTel='" + OfficeTel + '\'' +
                    ", HomeTel='" + HomeTel + '\'' +
                    ", Mobile='" + Mobile + '\'' +
                    ", Fax='" + Fax + '\'' +
                    ", QQ='" + QQ + '\'' +
                    ", Msn='" + Msn + '\'' +
                    ", CardType='" + CardType + '\'' +
                    ", CardNum='" + CardNum + '\'' +
                    ", WebSite='" + WebSite + '\'' +
                    ", Question='" + Question + '\'' +
                    ", Answer='" + Answer + '\'' +
                    ", RegDate='" + RegDate + '\'' +
                    ", LastLoginDate='" + LastLoginDate + '\'' +
                    ", LastLoginIP='" + LastLoginIP + '\'' +
                    ", LoginTime=" + LoginTime +
                    ", AdvancePayment=" + AdvancePayment +
                    ", Score=" + Score +
                    ", RankScore=" + RankScore +
                    ", LockAdvancePayment=" + LockAdvancePayment +
                    ", LockSocre=" + LockSocre +
                    ", CostMoney=" + CostMoney +
                    ", MemberRankGuid='" + MemberRankGuid + '\'' +
                    ", TradeCount=" + TradeCount +
                    ", IsForbid=" + IsForbid +
                    ", CreateUser='" + CreateUser + '\'' +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", ModifyUser='" + ModifyUser + '\'' +
                    ", ModifyTime='" + ModifyTime + '\'' +
                    ", IsDeleted=" + IsDeleted +
                    ", IsAgentID=" + IsAgentID +
                    ", AgentRankScore=" + AgentRankScore +
                    ", AgentValidity='" + AgentValidity + '\'' +
                    ", PaymentType=" + PaymentType +
                    ", CommendCondition=" + CommendCondition +
                    ", AreaCode='" + AreaCode + '\'' +
                    ", WW='" + WW + '\'' +
                    ", AppKey='" + AppKey + '\'' +
                    ", Tshou=" + Tshou +
                    ", TEmail=" + TEmail +
                    ", IsSetTemplates=" + IsSetTemplates +
                    '}';
        }
    }
}
