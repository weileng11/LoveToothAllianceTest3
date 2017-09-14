package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/11.
 * contact：weileng143@163.com
 *
 * @description
 */

public class AllOrderRespBean {

//  case 1://待付款
//    _url += '&IsBack=0&PaymentStatus=0';break;
//				case 2://待使用
//    //_url += '&IsBack=0&AlreadPayPrice=0.01';break;
//    _url += '&IsBack=0&OderStatus=5';break;//测试
//				case 3://待评价
//    _url += '&IsBack=0&OderStatus=5';break;
//				case 4://售后
//    _url += '&IsBack=1';break;

//    isBack=1是退款退货的订单
    public List<DataBean> data;

    public static class DataBean {
        /**
         * isBack=1是退款退货的订单
         * <p>
         * Guid : a72c659a-d01e-439b-9871-89c89d1d9e33
         * isReturn : 0
         * OrderNumber : 201708110856788
         * DispatchPrice : 0.0
         * IsBack : 0
         * PaymentStatus : 0
         * OderStatus : 1 状态码  5已完成
         * DispatchModeName :
         * ShipmentNumber : 运单编号
         * PaymentStatus1 : 0
         * ShipmentStatus : 0  3备货中 1已发货 2已收货
         * AlreadPayPrice : 0.0
         * ShouldPayPrice : 1380.0
         * CreateTime : 2017-08-11T08:56:32
         * ProcessState : null
         * DispatchModeName1 :
         * ShipmentNumber1 : 运单编号
         * OderStatus1 : 1
         * PaymentStatus2 : 0  0未支付 2 已支付
         * ShipmentStatus1 : 0
         * Goods : [{"Name":"伊帝沙纯棉小脚休闲裤","BuyPrice":138,"BuyNumber":10,"Attributes":"颜色:巧克力; 裤子尺码:30; ","OriginalImge":"http://www.yidisha.com.cn/ImgUpload/20161109161030271.jpg","ProductGuid":"17b706d9-eeba-41be-85f8-8483bb7bf700","IsComment":0}]
         */

        public String Guid;
        public int isReturn;
        public String OrderNumber;
        public double DispatchPrice;
        public int IsBack;
        public int PaymentStatus;
        public int OderStatus;
        public String DispatchModeName;
        public String ShipmentNumber;
        public int PaymentStatus1;
        public int ShipmentStatus;
        public double AlreadPayPrice;
        public double ShouldPayPrice;
        public String CreateTime;
        public Object ProcessState;
        public String DispatchModeName1;
        public String ShipmentNumber1;
        public int OderStatus1;
        public int PaymentStatus2;
        public int ShipmentStatus1;
        public List<GoodsBean> Goods;

        public static class GoodsBean {
            /**
             * Name : 伊帝沙纯棉小脚休闲裤
             * BuyPrice : 138.0
             * BuyNumber : 10
             * Attributes : 颜色:巧克力; 裤子尺码:30;
             * OriginalImge : http://www.yidisha.com.cn/ImgUpload/20161109161030271.jpg
             * ProductGuid : 17b706d9-eeba-41be-85f8-8483bb7bf700
             * IsComment : 0   1已评价
             */

            public String Name;
            public double BuyPrice;
            public int BuyNumber;
            public String Attributes;
            public String OriginalImge;
            public String ProductGuid;
            public int IsComment;

            @Override
            public String toString() {
                return "GoodsBean{" +
                        "Name='" + Name + '\'' +
                        ", BuyPrice=" + BuyPrice +
                        ", BuyNumber=" + BuyNumber +
                        ", Attributes='" + Attributes + '\'' +
                        ", OriginalImge='" + OriginalImge + '\'' +
                        ", ProductGuid='" + ProductGuid + '\'' +
                        ", IsComment=" + IsComment +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "Guid='" + Guid + '\'' +
                    ", isReturn=" + isReturn +
                    ", OrderNumber='" + OrderNumber + '\'' +
                    ", DispatchPrice=" + DispatchPrice +
                    ", IsBack=" + IsBack +
                    ", PaymentStatus=" + PaymentStatus +
                    ", OderStatus=" + OderStatus +
                    ", DispatchModeName='" + DispatchModeName + '\'' +
                    ", ShipmentNumber='" + ShipmentNumber + '\'' +
                    ", PaymentStatus1=" + PaymentStatus1 +
                    ", ShipmentStatus=" + ShipmentStatus +
                    ", AlreadPayPrice=" + AlreadPayPrice +
                    ", ShouldPayPrice=" + ShouldPayPrice +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", ProcessState=" + ProcessState +
                    ", DispatchModeName1='" + DispatchModeName1 + '\'' +
                    ", ShipmentNumber1='" + ShipmentNumber1 + '\'' +
                    ", OderStatus1=" + OderStatus1 +
                    ", PaymentStatus2=" + PaymentStatus2 +
                    ", ShipmentStatus1=" + ShipmentStatus1 +
                    ", Goods=" + Goods +
                    '}';
        }
    }
}
