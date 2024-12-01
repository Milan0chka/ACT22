package com.example.act22.data.model

enum class AssetType {
    CRYPTO, STOCK, ALL
}

// Superclass Asset
sealed class Asset(
    open val ID: String,
    open val name: String,
    open var price: Double,
    open val iconUrl: String // New field for the icon URL
)

// TechStock subclass
data class TechStock(
    override val ID: String,
    override val name: String,
    override var price: Double,
    override val iconUrl: String, // Icon URL for each tech stock
    val marketCap: Double, // Additional field for tech stocks
    val sector: String, // New extra field for tech stocks
) : Asset(ID, name, price, iconUrl)

// Crypto subclass
data class Crypto(
    override val ID: String,
    override val name: String,
    override var price: Double,
    override val iconUrl: String, // Icon URL for each crypt
    val blockchain: String, // Blockchain technology for the crypto
    val marketDominance: Double, // New extra field for crypto
) : Asset(ID, name, price, iconUrl)



// Updated sample tech stocks with the correct order for `iconUrl`
val techStocks = listOf(
    TechStock("AAPL", "Apple", 175.50, "https://cdn.prod.website-files.com/62b0e6308cc691625470b227/62dec0259f18b71442a15966_Apple-Logo.png", 2.5E12, "Consumer Electronics"),
    TechStock("MSFT", "Microsoft", 330.10, "https://cdn.prod.website-files.com/5ee732bebd9839b494ff27cd/5eef3a3260847d0d2783a76d_Microsoft-Logo-PNG-Transparent-Image.png", 2.4E12, "Software"),
    TechStock("GOOGL", "Google", 2780.40, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png", 1.8E12, "Search & Advertising"),
    TechStock("AMZN", "Amazon", 3450.15, "https://static-00.iconduck.com/assets.00/amazon-icon-512x512-qj1xkn8x.png", 1.7E12, "E-commerce"),
    TechStock("TSLA", "Tesla", 900.50, "https://upload.wikimedia.org/wikipedia/commons/e/e8/Tesla_logo.png", 1.1E12, "Electric Vehicles"),
    TechStock("NVDA", "NVIDIA", 500.60, "https://upload.wikimedia.org/wikipedia/en/thumb/9/99/Nvidia_logo.svg/768px-Nvidia_logo.svg.png", 0.6E12, "Semiconductors"),
    TechStock("META", "Meta", 320.50, "https://upload.wikimedia.org/wikipedia/commons/0/0f/Meta_Platforms_Inc._logo.png", 1.0E12, "Social Media"),
    TechStock("INTC", "Intel", 58.80, "https://upload.wikimedia.org/wikipedia/commons/c/c9/Intel-logo.png", 0.2E12, "Semiconductors"),
    TechStock("AMD", "AMD", 110.70, "https://banner2.cleanpng.com/20180403/dkq/avhnmjq47.webp", 0.15E12, "Semiconductors"),
    TechStock("IBM", "IBM", 145.30, "https://upload.wikimedia.org/wikipedia/commons/5/51/IBM_logo.png", 0.11E12, "Cloud & AI"),
    TechStock("CSCO", "Cisco", 55.20, "https://upload.wikimedia.org/wikipedia/commons/6/64/Cisco-logo.png", 0.2E12, "Networking"),
    TechStock("ORCL", "Oracle", 90.30, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Oracle_logo.svg/2560px-Oracle_logo.svg.png", 0.3E12, "Enterprise Software"),
    TechStock("ADBE", "Adobe", 630.40, "https://i.pinimg.com/736x/56/3a/a2/563aa2189ef92dc242a7db5b91078804.jpg", 0.4E12, "Creative Software")
)

// Updated sample crypto assets with the correct order for `iconUrl`
val cryptoAssets = listOf(
    Crypto("BTC", "Bitcoin", 48000.50, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png", "Bitcoin", 45.0),
    Crypto("ETH", "Ethereum", 3500.75, "https://cryptologos.cc/logos/ethereum-eth-logo.png", "Ethereum", 20.0),
    Crypto("ADA", "Cardano", 1.25, "https://cryptologos.cc/logos/cardano-ada-logo.png", "Cardano", 4.0),
    Crypto("BNB", "Binance Coin", 500.90, "https://cryptologos.cc/logos/binance-coin-bnb-logo.png", "Binance Chain", 6.0),
    Crypto("SOL", "Solana", 150.50, "https://cryptologos.cc/logos/solana-sol-logo.png", "Solana", 2.5),
    Crypto("DOT", "Polkadot", 28.30, "https://cryptologos.cc/logos/polkadot-new-dot-logo.png", "Polkadot", 1.5),
    Crypto("AVAX", "Avalanche", 85.60, "https://cryptologos.cc/logos/avalanche-avax-logo.png", "Avalanche", 1.8),
    Crypto("LINK", "Chainlink", 35.75, "https://cryptologos.cc/logos/chainlink-link-logo.png", "Ethereum", 0.8),
    Crypto("DOGE", "Dogecoin", 0.30, "https://cryptologos.cc/logos/dogecoin-doge-logo.png", "Dogecoin", 2.0)
)


