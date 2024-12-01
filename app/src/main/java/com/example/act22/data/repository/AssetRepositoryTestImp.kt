package com.example.act22.data.repository

import android.util.Log
import com.example.act22.data.model.Asset
import com.example.act22.data.model.AssetType
import com.example.act22.data.model.SortingCriteria
import com.example.act22.data.model.cryptoAssets
import com.example.act22.data.model.techStocks
import kotlinx.coroutines.delay
import kotlin.jvm.Throws
import kotlin.random.Random

class AssetRepositoryTestImp : AssetRepository {
    private val assets: List<Asset> = (techStocks + cryptoAssets).sortedBy { it.name }

    override suspend fun getAllAssets(): List<Asset> {
        return assets
    }

    override suspend fun searchAssets(search: String): List<Asset> {
        return assets.filter { asset ->
            asset.name.startsWith(search, ignoreCase = true)
        }
    }

    override suspend fun filterAssetsByType(type: AssetType): List<Asset> {
        return when (type) {
            AssetType.CRYPTO -> cryptoAssets
            AssetType.STOCK -> techStocks
            AssetType.ALL -> assets
        }
    }

    override suspend fun sortAssets(criteria: SortingCriteria): List<Asset> {
        return when (criteria) {
            SortingCriteria.ASC -> assets.sortedBy { it.price }
            SortingCriteria.DESC -> assets.sortedByDescending { it.price }
            SortingCriteria.ALPHABET -> assets.sortedBy { it.name }
        }
    }

    override suspend fun findAsset(id: String): Asset {
        val asset = assets.find { it.ID == id }
        return asset ?: throw IllegalArgumentException("Asset with id $id not found")
    }

    override suspend fun getYearlyHistoryPricePoints(id: String): List<Double> {
        val prices = List(12) { Random.nextDouble() * 1000f }
        val asset = findAsset(id)
        asset.price = prices[11]
        return prices
    }

    override suspend fun getDailyHistoryPricePoints(id: String): List<Double> {
        val prices = List(24) { Random.nextDouble() * 1000f }
        val asset = findAsset(id)
        asset.price = prices[23]
        return prices
    }

}
