package com.k_stock_market.web.service

import com.k_stock_market.web.entity.StockEntity
import com.k_stock_market.web.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileReader

import java.io.BufferedReader
import java.time.ZoneId
import java.time.ZonedDateTime

import java.util.ArrayList
import java.util.stream.Collectors.groupingBy

@Service
class FileReadService @Autowired constructor(
    var stockRepository: StockRepository
    ) {

    private val ROW_NUM_INDX = 0; // 행 번호
    private val CODE_INDX = 1; // 종목코드
    private val NAME_INDX = 2; // 종목명
    private val TIME_INDX = 3; // 시간
    private val CLOSING_PRICE_INDX = 4; // 종가
    private val DIFF_INDX = 5; // 대비
    private val OPEN_INDX = 6; // 시가
    private val HIGH_INDX = 7; // 고가
    private val LOW_INDX = 8 // 저가
    private val OFFER_INDX = 9 // 매도호가
    private val BID_INDX = 10 // 매수호가
    private val VOL_INDX = 11 // 거래량
    private val VOL_VALUE_INDX = 12 // 거래대금
    private val EX_FLAG_INDX = 13 // 예상체결가 구분 플래그
    private val EX_PRICE_INDX = 14 // 예상체결가
    private val EX_DIFF_INDX = 15 // 예상체결가 전일대비
    private val EX_VOL_INDX = 16 // 예상체결수량

    /**
     * csv 파일 읽기
     */
    fun fileRead():List<StockEntity> {
        val stocks = ArrayList<StockEntity>()
        var line: String?

        val bufferedReader = BufferedReader(FileReader("src/main/resources/files/stock.csv"))
        println(bufferedReader)

        // Kotlin에서 try-with-resource 문법은 use를 활용한다.
        BufferedReader(FileReader("src/main/resources/files/stock.csv")).use { fileReader ->

            // Read CSV header
            fileReader.readLine();

            line = fileReader.readLine();
            while(line != null) {
                val tokens = line!!.split(",")
                if(tokens.size > 0) {
                    var exDiff: Double? = null
                    if(tokens[EX_DIFF_INDX].isNotEmpty()){
                        exDiff = tokens[EX_DIFF_INDX].toDouble()
                    }

                    val stock = StockEntity(
                        null,
                        tokens[ROW_NUM_INDX].toLong(),
                        tokens[CODE_INDX],
                        tokens[NAME_INDX],
                        tokens[TIME_INDX],
                        tokens[CLOSING_PRICE_INDX].toLong(),
                        tokens[DIFF_INDX].toLong(),
                        tokens[OPEN_INDX].toLong(),
                        tokens[HIGH_INDX].toLong(),
                        tokens[LOW_INDX].toLong(),
                        tokens[OFFER_INDX].toLong(),
                        tokens[BID_INDX].toLong(),
                        tokens[VOL_INDX].toLong(),
                        tokens[VOL_VALUE_INDX].toLong(),
                        tokens[EX_FLAG_INDX].toLong(),
                        tokens[EX_PRICE_INDX].toLong(),
                        exDiff,
                        tokens[EX_VOL_INDX].toLong(),
                        ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
                        )
                    stocks.add(stock)
                }
                line = fileReader.readLine()
            }

            for(stock in stocks) {
                println(stock)
            }
            stockRepository.saveAll(stocks)
        }
        return stocks
    }

    fun getStocks():MutableMap<ZonedDateTime,ArrayList<StockEntity>>?{
        val stocks:List<StockEntity> = stockRepository.findAll()
        var map:MutableMap<ZonedDateTime,ArrayList<StockEntity>> = HashMap()
        for(stock in stocks){
            stock.registeredTime?.let {
                if(map[it] != null){
                    map[it]?.add(stock)
                } else {
                    map.put(it,ArrayList())
                }
            }
        }
        for(key in map.keys){
            map[key]?.sortedWith(compareBy {it.id})
        }
        return map
    }
}