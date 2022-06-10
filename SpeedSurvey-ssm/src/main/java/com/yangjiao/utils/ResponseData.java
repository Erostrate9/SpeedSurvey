package com.yangjiao.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 500;
    private Map<String,Object> map = new HashMap<>();

    private ResponseData(){

    }
    public static ResponseData create(){
        return new ResponseData();
    }

    public ResponseData success(){
        this.map.put("msg","操作成功");
        this.map.put("code",SUCCESS_CODE);
        return this;
    }

    public ResponseData setData(Object data){
        this.map.put("data",data);
        return this;
    }
    public ResponseData success(String msg){
        this.map.put("msg",msg);
        this.map.put("code",SUCCESS_CODE);
        return this;
    }

    public ResponseData error(){
        this.map.put("msg","操作失败");
        this.map.put("code",ERROR_CODE);
        return this;
    }
    public ResponseData error(String msg){
        this.map.put("msg",msg);
        this.map.put("code",ERROR_CODE);
        return this;
    }

    public Map<String,Object> getResponse(){
        return this.map;
    }

}
