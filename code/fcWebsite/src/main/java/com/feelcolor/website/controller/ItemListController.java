package com.feelcolor.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("itemList")
public class ItemListController {
    
    @RequestMapping("/all")
    public String itemList(){
        return "itemList";
    }

}
