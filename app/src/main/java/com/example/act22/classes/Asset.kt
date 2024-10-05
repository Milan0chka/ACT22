package com.example.act22.classes

// Superclass Asset
sealed class Asset(
    open val name: String,
    open val price: Double
)

// TechStock subclass
data class TechStock(
    override val name: String,
    override val price: Double,
    val marketCap: Double, // Additional field for tech stocks
    val sector: String // New extra field for tech stocks
) : Asset(name, price)

// Crypto subclass
data class Crypto(
    override val name: String,
    override val price: Double,
    val blockchain: String, // Blockchain technology for the crypto
    val marketDominance: Double // New extra field for crypto
) : Asset(name, price)

