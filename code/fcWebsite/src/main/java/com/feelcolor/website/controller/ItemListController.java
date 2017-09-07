package com.feelcolor.website.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("itemList")
public class ItemListController {

    @RequestMapping("/all")
    @ApiOperation(value = "商品列表", httpMethod = "GET")
    public String itemList() {
        return "mall/itemList";
    }

}
