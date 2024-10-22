package com.example.act22.data

import Crypto
import TechStock

data class Portfolio(
    val userID: Int,
    val techStocks: List<TechStock>,
    val cryptos: List<Crypto>
) {
    init {
        require(techStocks.size <= 10) { "techStocks can have at most 10 items" }
        require(cryptos.size <= 3) { "cryptos can have at most 3 items" }
    }
}
