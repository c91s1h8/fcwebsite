package com.feelcolor.website.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("itemDetail")
public class ItemDetailController extends BaseController {

    @RequestMapping("/detail")
    @ApiOperation(value = "商品详情", httpMethod = "GET")
    public String Detail() {
        return "itemDetail";
    }
}
