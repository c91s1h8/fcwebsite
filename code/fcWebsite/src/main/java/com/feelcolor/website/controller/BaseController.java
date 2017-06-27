package com.feelcolor.website.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

   public Map<String,Object> ajaxResult(String stat,String code,String msg){
       Map<String,Object> rs = new HashMap<String,Object>();
       rs.put("stat", stat);
       rs.put("code", code);
       rs.put("msg", msg);
       return rs;
   }
}
