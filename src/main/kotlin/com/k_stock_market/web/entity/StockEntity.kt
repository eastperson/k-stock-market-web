package com.k_stock_market.web.entity

import lombok.NoArgsConstructor
import lombok.ToString
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "stock")
@ToString
class StockEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                 val id: Long? = null,
                       val dailyRow:Long?, val stockCode:String?, val name:String?, val checkTime:String?,
                       val closingPrice:Long?, val diff:Long?, val openPrice:Long?, val high:Long?, val low:Long?,
                       val offer:Long?, val bid:Long?, val vol:Long?, val volValue:Long?, val exFlag:Long?,
                       val exPrice:Long?, val exDiff:Double?, val exVol:Long?,val registeredTime:ZonedDateTime?) {
    constructor():this(
        null,null,null,null,null,null,null,null,null,null,
        null,null,null,null,null,null,null,null,null
    )
}
