package com.feelcolor.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("itemDetail")
public class ItemDetailController extends BaseController {

    @RequestMapping("/detail")
    public String Detail(){
        return "itemDetail";
    }
}
