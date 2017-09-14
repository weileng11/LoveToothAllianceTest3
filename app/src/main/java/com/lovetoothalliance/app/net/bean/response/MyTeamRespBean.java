package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/21.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MyTeamRespBean {

    /**
     * data : {"table":{"p1":0,"p2":0,"p3":0,"sale1":0,"sale2":0,"sale3":0},"team_list":[]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * table : {"p1":0,"p2":0,"p3":0,"sale1":0,"sale2":0,"sale3":0}
         * team_list : []
         */

        public TableBean table;
        public List<?> team_list;

        public static class TableBean {
            /**
             * p1 : 0
             * p2 : 0
             * p3 : 0
             * sale1 : 0
             * sale2 : 0
             * sale3 : 0
             */

            public int p1;
            public int p2;
            public int p3;
            public int sale1;
            public int sale2;
            public int sale3;
        }
    }
}
