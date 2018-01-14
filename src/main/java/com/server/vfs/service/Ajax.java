package com.server.vfs.service;

import java.util.HashMap;
import java.util.Map;

public class Ajax {

    public static Map<String,Object> successResponse(Object obj){
        Map<String, Object> resp = new HashMap<>();
        resp.put("result","success");
        resp.put("data",obj);
        return resp;
    }

    public static Map<String,Object> emptyResponse(){
        Map<String, Object> resp = new HashMap<>();
        resp.put("result","success");
        return resp;
    }

    public static Map<String,Object> errorResponse(String errorMsg){
        Map<String, Object> resp = new HashMap<>();
        resp.put("result","error");
        resp.put("data",errorMsg);
        return resp;
    }

}
