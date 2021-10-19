package com.k_stock_market.web.properties

import lombok.Getter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties
@Getter
@PropertySource("classpath:properties/path.properties")
class PathProperties {

    @Value("\${path}")
    val path: String? = null
}