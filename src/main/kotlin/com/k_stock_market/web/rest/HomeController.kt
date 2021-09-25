package com.k_stock_market.web.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home")
class HomeController {

    @GetMapping("")
    fun home(): HashMap<String,String> {
        val map = HashMap<String,String>()
        map.put("key","value")
        return map;
    }

}