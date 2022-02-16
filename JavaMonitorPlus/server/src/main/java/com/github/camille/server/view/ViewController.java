package com.github.camille.server.view;


import com.github.camille.server.remote.parm.AddressParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 23:29
 */
@Controller
public class ViewController {
    @Autowired
    private AddressParm addressParm;

    @RequestMapping(value = "/")
    public String index(ModelMap model) throws IOException {
//        List<Map<String,Object>> list = new ArrayList<>();
//        for (Address address : addressParm.getServe()) {
//            list.add(viewService.getIndex(address));
//        }
//        model.addAttribute("list",list);
        return "myindex";
    }

    @RequestMapping(value = "/monitor")
    public String monitor(){
        return "monitor";
    }

}
