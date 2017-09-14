package com.utils;

/**
 * classes:com.iptv.utils.StringUtils
 *
 * @author ly
 * @date 2016/6/15
 * @time 12:31
 * @description
 */
public class StringUtils {

    public String rtspToHttp(String response) {
        return response.replace("rtsp", "http");
    }


}
