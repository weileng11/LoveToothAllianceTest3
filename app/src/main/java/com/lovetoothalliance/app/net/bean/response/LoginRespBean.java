package com.lovetoothalliance.app.net.bean.response;

/**
 * Author:lt
 * time:2017/8/5.
 * contact：weileng143@163.com
 *
 * @description
 */

public class LoginRespBean{

    /**Stater
     * data : {"Stater":"1","result":"登陆成功","User":{"Guid":"74310ba4-41e1-4a28-b968-c0d8d5a2e283","MemLoginID":"13725150568","Email":"13725150568@qq.com","Pwd":"e10adc3949ba59abbe56e057f20f883e","PayPwd":"e10adc3949ba59abbe56e057f20f883e","Sex":0,"Age":0,"Birthday":"1900-01-01 00:00:00","CreditMoney":0,"Photo":"http://7y2ow0.com1.z0.glb.clouddn.com/apicloud/f0c1781741c0b06f95ba85794b8a2ced.jpg","RealName":"如果","Area":"广东省广州市天河区","Vocation":"","Address":"中山大道西101号","Postalcode":"","OfficeTel":"","HomeTel":"","Mobile":"","Fax":"","QQ":"","Msn":"4.6","CardType":"","CardNum":"","WebSite":"","Question":"","Answer":"","RegDate":"2016-11-21 11:36:43","LastLoginDate":"2017-08-09 15:48:49","LastLoginIP":"183.63.173.122","LoginTime":204,"AdvancePayment":0,"Score":0,"RankScore":0,"LockAdvancePayment":0,"LockSocre":0,"CostMoney":0,"MemberRankGuid":"05c29601-930f-4f35-9d36-3c9839914276","TradeCount":0,"IsForbid":0,"CreateUser":"Admin","CreateTime":"2016-11-21 11:36:43","ModifyUser":"Admin","ModifyTime":"2016-12-26 16:40:07","IsDeleted":0,"IsAgentID":1,"AgentRankScore":0,"AgentValidity":"2020-07-16 00:00:00","PaymentType":0,"CommendCondition":0,"AreaCode":"019001005","WW":"","Tshou":0,"TEmail":0,"IsSetTemplates":1}}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * Stater : 1
         * result : 登陆成功
         * User : {"Guid":"74310ba4-41e1-4a28-b968-c0d8d5a2e283","MemLoginID":"13725150568","Email":"13725150568@qq.com","Pwd":"e10adc3949ba59abbe56e057f20f883e","PayPwd":"e10adc3949ba59abbe56e057f20f883e","Sex":0,"Age":0,"Birthday":"1900-01-01 00:00:00","CreditMoney":0,"Photo":"http://7y2ow0.com1.z0.glb.clouddn.com/apicloud/f0c1781741c0b06f95ba85794b8a2ced.jpg","RealName":"如果","Area":"广东省广州市天河区","Vocation":"","Address":"中山大道西101号","Postalcode":"","OfficeTel":"","HomeTel":"","Mobile":"","Fax":"","QQ":"","Msn":"4.6","CardType":"","CardNum":"","WebSite":"","Question":"","Answer":"","RegDate":"2016-11-21 11:36:43","LastLoginDate":"2017-08-09 15:48:49","LastLoginIP":"183.63.173.122","LoginTime":204,"AdvancePayment":0,"Score":0,"RankScore":0,"LockAdvancePayment":0,"LockSocre":0,"CostMoney":0,"MemberRankGuid":"05c29601-930f-4f35-9d36-3c9839914276","TradeCount":0,"IsForbid":0,"CreateUser":"Admin","CreateTime":"2016-11-21 11:36:43","ModifyUser":"Admin","ModifyTime":"2016-12-26 16:40:07","IsDeleted":0,"IsAgentID":1,"AgentRankScore":0,"AgentValidity":"2020-07-16 00:00:00","PaymentType":0,"CommendCondition":0,"AreaCode":"019001005","WW":"","Tshou":0,"TEmail":0,"IsSetTemplates":1}
         */


        public String Stater;
        public String result;
        public UserBean User;

        public static class UserBean {
            /**
             * Guid : 74310ba4-41e1-4a28-b968-c0d8d5a2e283
             * MemLoginID : 13725150568
             * Email : 13725150568@qq.com
             * Pwd : e10adc3949ba59abbe56e057f20f883e
             * PayPwd : e10adc3949ba59abbe56e057f20f883e
             * Sex : 0
             * Age : 0
             * Birthday : 1900-01-01 00:00:00
             * CreditMoney : 0
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
             * Msn : 4.6
             * CardType :
             * CardNum :
             * WebSite :
             * Question :
             * Answer :
             * RegDate : 2016-11-21 11:36:43
             * LastLoginDate : 2017-08-09 15:48:49
             * LastLoginIP : 183.63.173.122
             * LoginTime : 204
             * AdvancePayment : 0
             * Score : 0
             * RankScore : 0
             * LockAdvancePayment : 0
             * LockSocre : 0
             * CostMoney : 0
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
            public int CreditMoney;
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
            public int AdvancePayment;
            public int Score;
            public int RankScore;
            public int LockAdvancePayment;
            public int LockSocre;
            public int CostMoney;
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
            public int Tshou;
            public int TEmail;
            public int IsSetTemplates;
        }
    }
}
