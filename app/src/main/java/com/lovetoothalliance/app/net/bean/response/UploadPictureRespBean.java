package com.lovetoothalliance.app.net.bean.response;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/16.
 * contact：weileng143@163.com
 *
 * @description
 */

public class UploadPictureRespBean {


    /**
     * data : {"State":1,"Message":"上传成功","data":["http://aclmmanage.jshec.cn:8856/images/3ad187acae8948fb94d487b2fe1755f3.png"]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * State : 1
         * Message : 上传成功
         * data : ["http://aclmmanage.jshec.cn:8856/images/3ad187acae8948fb94d487b2fe1755f3.png"]
         */

        public int State;
        public String Message;
        public List<String> data;
    }
}
