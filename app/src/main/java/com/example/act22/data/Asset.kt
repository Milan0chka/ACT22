// Superclass Asset
sealed class Asset(
    open val name: String,
    open val price: Double,
    open val iconUrl: String // New field for the icon URL
)

// TechStock subclass
data class TechStock(
    override val name: String,
    override val price: Double,
    val marketCap: Double, // Additional field for tech stocks
    val sector: String, // New extra field for tech stocks
    override val iconUrl: String // Icon URL for each tech stock
) : Asset(name, price, iconUrl)

// Crypto subclass
data class Crypto(
    override val name: String,
    override val price: Double,
    val blockchain: String, // Blockchain technology for the crypto
    val marketDominance: Double, // New extra field for crypto
    override val iconUrl: String // Icon URL for each crypto
) : Asset(name, price, iconUrl)

// Sample tech stocks with icons
val techStocks = listOf(
    TechStock("Apple", 175.50, 2.5E12, "Consumer Electronics", "https://cdn.prod.website-files.com/62b0e6308cc691625470b227/62dec0259f18b71442a15966_Apple-Logo.png"),
    TechStock("Microsoft", 330.10, 2.4E12, "Software", "https://cdn.prod.website-files.com/5ee732bebd9839b494ff27cd/5eef3a3260847d0d2783a76d_Microsoft-Logo-PNG-Transparent-Image.png"),
    TechStock("Google", 2780.40, 1.8E12, "Search & Advertising", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png"),
    TechStock("Amazon", 3450.15, 1.7E12, "E-commerce", "https://static-00.iconduck.com/assets.00/amazon-icon-512x512-qj1xkn8x.png"),
    TechStock("Tesla", 900.50, 1.1E12, "Electric Vehicles", "https://upload.wikimedia.org/wikipedia/commons/e/e8/Tesla_logo.png"),
    TechStock("NVIDIA", 500.60, 0.6E12, "Semiconductors", "https://upload.wikimedia.org/wikipedia/en/thumb/9/99/Nvidia_logo.svg/768px-Nvidia_logo.svg.png"),
    TechStock("Meta", 320.50, 1.0E12, "Social Media", "https://upload.wikimedia.org/wikipedia/commons/0/0f/Meta_Platforms_Inc._logo.png"),
    TechStock("Intel", 58.80, 0.2E12, "Semiconductors", "https://upload.wikimedia.org/wikipedia/commons/c/c9/Intel-logo.png"),
    TechStock("AMD", 110.70, 0.15E12, "Semiconductors", "https://upload.wikimedia.org/wikipedia/commons/5/50/AMD_logo.png"),
    TechStock("IBM", 145.30, 0.11E12, "Cloud & AI", "https://upload.wikimedia.org/wikipedia/commons/5/51/IBM_logo.png"),
    TechStock("Cisco", 55.20, 0.2E12, "Networking", "https://upload.wikimedia.org/wikipedia/commons/6/64/Cisco-logo.png"),
    TechStock("Oracle", 90.30, 0.3E12, "Enterprise Software", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Oracle_logo.svg/2560px-Oracle_logo.svg.png"),
    TechStock("Adobe", 630.40, 0.4E12, "Creative Software", "https://upload.wikimedia.org/wikipedia/commons/3/31/Adobe_Systems_logo_and_wordmark.png")
)

// Sample crypto assets with icons
val cryptoAssets = listOf(
    Crypto("Bitcoin", 48000.50, "Bitcoin", 45.0, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png"),
    Crypto("Ethereum", 3500.75, "Ethereum", 20.0, "https://cryptologos.cc/logos/ethereum-eth-logo.png"),
    Crypto("Cardano", 1.25, "Cardano", 4.0, "https://cryptologos.cc/logos/cardano-ada-logo.png"),
    Crypto("Binance Coin", 500.90, "Binance Chain", 6.0, "https://cryptologos.cc/logos/binance-coin-bnb-logo.png"),
    Crypto("Solana", 150.50, "Solana", 2.5, "https://cryptologos.cc/logos/solana-sol-logo.png"),
    Crypto("Polkadot", 28.30, "Polkadot", 1.5, "https://cryptologos.cc/logos/polkadot-new-dot-logo.png"),
    Crypto("Avalanche", 85.60, "Avalanche", 1.8, "https://cryptologos.cc/logos/avalanche-avax-logo.png"),
    Crypto("Chainlink", 35.75, "Ethereum", 0.8, "https://cryptologos.cc/logos/chainlink-link-logo.png"),
    Crypto("Dogecoin", 0.30, "Dogecoin", 2.0, "https://cryptologos.cc/logos/dogecoin-doge-logo.png")
)

