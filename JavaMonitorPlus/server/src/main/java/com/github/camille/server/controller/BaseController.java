package com.github.camille.server.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-20 15:52
 **/
@Controller
public class BaseController {

    public String getResponse(int code, String msg) {
        Map<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        return JSON.toJSONString(map);
    }


}
