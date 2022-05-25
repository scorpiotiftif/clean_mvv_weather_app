package fr.francoisgaucher.domain.model

import androidx.annotation.VisibleForTesting
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
) {
    @VisibleForTesting
    constructor() : this(
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        "",
        "",
        1.0,
        1.0,
        "",
        "",
        "",
        Calendar.getInstance().time,
        "",
        1,
        AqiInfo(
            "",
            1.0,
            ""
        )
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AirQuality

        if (co != other.co) return false
        if (n02 != other.n02) return false
        if (ozone != other.ozone) return false
        if (pm10 != other.pm10) return false
        if (pm25 != other.pm25) return false
        if (s02 != other.s02) return false
        if (city != other.city) return false
        if (countryCode != other.countryCode) return false
        if (lat != other.lat) return false
        if (lng != other.lng) return false
        if (placeName != other.placeName) return false
        if (postalCode != other.postalCode) return false
        if (state != other.state) return false
        if (updatedAt != other.updatedAt) return false
        if (division != other.division) return false
        if (aqi != other.aqi) return false
        if (aqiInfo != other.aqiInfo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = co.hashCode()
        result = 31 * result + n02.hashCode()
        result = 31 * result + ozone.hashCode()
        result = 31 * result + pm10.hashCode()
        result = 31 * result + pm25.hashCode()
        result = 31 * result + s02.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + countryCode.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + lng.hashCode()
        result = 31 * result + placeName.hashCode()
        result = 31 * result + postalCode.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + division.hashCode()
        result = 31 * result + aqi
        result = 31 * result + aqiInfo.hashCode()
        return result
    }


}

data class AqiInfo(
    val pollutant: String,
    val concentration: Double,
    val category: String,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AqiInfo

        if (pollutant != other.pollutant) return false
        if (concentration != other.concentration) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pollutant.hashCode()
        result = 31 * result + concentration.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}
