package com.xylink.wechat.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
public class JsonBuilder {
    /**
     * 将一个实体类转换成json字符串
     * @param object
     * @return
     */
    public static String getString(Object object){
        //安全判断
        if (object==null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String back;
        try{
            back=mapper.writeValueAsString(object);
        }catch (Exception e){
            //抛出一个自定义异常
            throw new RuntimeException("json字符转换失败！-object-"+object,e);
        }
        return back;
    }
}
