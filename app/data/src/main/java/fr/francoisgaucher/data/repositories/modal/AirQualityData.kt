package fr.francoisgaucher.data.repositories.modal

import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.model.AqiInfo
import java.time.ZonedDateTime
import java.util.*

data class AirQualityData(
    val message: String,
    val stations: List<Station>?
) {
    fun convertTo(): List<AirQuality> {
        val airQuality = mutableListOf<AirQuality>()

        stations?.let {
            for (station in it) {
                airQuality.add(
                    AirQuality(
                        co = station.CO,
                        n02 = station.NO2,
                        ozone = station.OZONE,
                        pm10 = station.PM10,
                        pm25 = station.PM25,
                        s02 = station.SO2,
                        city = station.city,
                        countryCode = station.countryCode,
                        lat = station.lat,
                        lng = station.lng,
                        placeName = station.placeName,
                        postalCode = station.postalCode,
                        state = station.state,
                        updatedAt = Date.from(ZonedDateTime.parse(station.updatedAt).toInstant()),
                        division = station.division,
                        aqi = station.AQI,
                        aqiInfo = station.aqiInfo.convertTo()
                    )
                )
            }
        }


        return airQuality
    }
}

data class Station(
    val AQI: Int,
    val CO: Double,
    val NO2: Double,
    val OZONE: Double,
    val PM10: Double,
    val PM25: Double,
    val SO2: Double,
    val aqiInfo: AqiInfoData,
    val city: String,
    val countryCode: String,
    val division: String,
    val lat: Double,
    val lng: Double,
    val placeName: String,
    val postalCode: String,
    val state: String,
    val updatedAt: String
)

data class AqiInfoData(
    val category: String,
    val concentration: Double,
    val pollutant: String
) {
    fun convertTo(): AqiInfo {
        return AqiInfo(pollutant = pollutant, concentration = concentration, category = category)
    }
}