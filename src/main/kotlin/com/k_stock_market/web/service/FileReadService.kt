package com.k_stock_market.web.service

import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.StatefulBeanToCsvBuilder
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.io.FileWriter
import java.util.*

@Service
@RequiredArgsConstructor
class FileReadService {

    fun fileRead() {
        val customers = Arrays.asList({})
        var fileWriter = FileWriter("customerList.csv")

        val mappingStrategy = ColumnPositionMappingStrategy<customer>()
        mappingStrategy.setType(Customer::class.java)
        mappingStrategy.setColumnMapping("id", "name", "address", "age")

        var beanToCsv = StatefulBeanToCsvBuilder<customer>(fileWriter)
            .withMappingStrategy(mappingStrategy)
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .build()

        beanToCsv.write(customers)
    }

}

data class Stock(val row:Long,val code:String,val name:String,val time:String,
                    val 종가:Long,val 대비:Long
                 )