package com.lovetoothalliance.app.net.bean.response;

/**
 * Author:lt
 * time:2017/8/9.
 * contact：weileng143@163.com
 *
 * @description
 */

public class SpecialistOrderRespBean {

    /**
     * data : {"Stater":"1","result":{"orderNumber":"201708091650975","shouldPayPrice":138}}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * Stater : 1
         * result : {"orderNumber":"201708091650975","shouldPayPrice":138}
         */

        public String Stater;
        public ResultBean result;

        public static class ResultBean {
            /**
             * orderNumber : 201708091650975
             * shouldPayPrice : 138.0
             */

            public String orderNumber;
            public double shouldPayPrice;

            @Override
            public String toString() {
                return "ResultBean{" +
                        "orderNumber='" + orderNumber + '\'' +
                        ", shouldPayPrice=" + shouldPayPrice +
                        '}';
            }
        }
    }
}
