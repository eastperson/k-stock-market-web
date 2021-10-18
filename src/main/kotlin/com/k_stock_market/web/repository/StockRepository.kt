package com.k_stock_market.web.repository

import com.k_stock_market.web.entity.StockEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface StockRepository: CrudRepository<StockEntity,Long> {
    override fun findAll() : List<StockEntity>
}