package com.example.shop.base.json;

import lombok.Data;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result  {
        private String code;
        private String msg;
        private String data;
      public  static  String Result(String code,String msg){
          Map<String,Object>map=new HashMap<>();
          map.put("code",code);
          map.put("msg",msg);
          return JSON.toJSON(map).toString();
      }
    public  static  String Result(String code,String msg,Object data){
        Map<String,Object>map=new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data);
        return JSON.toJSON(map).toString();
    }

    public static String Result(RC rc) {
        Map<String,Object>map=new HashMap<>();
        map.put("code",rc.code);
        map.put("msg",rc.msg);
        return JSON.toJSON(map).toString();
    }
    public static String Result(RC rc,Object data) {
        Map<String,Object>map=new HashMap<>();
        map.put("code",rc.code);
        map.put("msg",rc.msg);
        map.put("data",data);
        return JSON.toJSON(map).toString();
    }
}
