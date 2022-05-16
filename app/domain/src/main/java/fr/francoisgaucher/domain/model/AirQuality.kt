package fr.francoisgaucher.domain.model

import java.util.*

data class AirQuality(
    val co: Double,
    val n02: Double,
    val ozone: Double,
    val pm10: Double,
    val pm25: Double,
    val s02: Double,
    val city: String,
    val countryCode: String,
    val lat: Double,
    val lng: Double,
    val placeName: String,
    val postalCode: String,
    val state: String,
    val updatedAt: Date,
    val division: String,
    val aqi: Int,
    val aqiInfo: AqiInfo
)

data class AqiInfo(
    val pollutant: String,
    val concentration: Double,
    val category: String,
)
