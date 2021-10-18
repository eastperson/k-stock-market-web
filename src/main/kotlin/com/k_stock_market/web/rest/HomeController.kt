package com.k_stock_market.web.rest

import com.k_stock_market.web.entity.StockEntity
import com.k_stock_market.web.service.FileReadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime
import java.util.ArrayList

@RestController
@RequestMapping("/home")
class HomeController
    @Autowired constructor(
    var fileReadService: FileReadService
    ) {

    @GetMapping("")
    fun home(): HashMap<String,String> {
        val map = HashMap<String,String>()
        map.put("key","value")
        return map;
    }

    @GetMapping("/stock")
    fun readAndSave(): List<StockEntity> {
        return fileReadService!!.fileRead()
    }

    @GetMapping("/get")
    fun getStock(): MutableMap<ZonedDateTime, ArrayList<StockEntity>>? {
        return fileReadService!!.getStocks()
    }
}