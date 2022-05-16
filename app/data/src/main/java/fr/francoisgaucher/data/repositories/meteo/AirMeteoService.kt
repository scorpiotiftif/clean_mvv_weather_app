package fr.francoisgaucher.data.repositories.meteo

import fr.francoisgaucher.data.repositories.modal.AirQualityData
import fr.francoisgaucher.domain.model.AirQuality
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface AirMeteoService {
    @GET("/latest/by-lat-lng")
    suspend fun getCurrentByCoordonate(@Query("lat") lat: Double, @Query("lng") lng: Double): Response<AirQualityData>

    @GET("/latest/by-lat-lng")
    suspend fun getCurrentByPostalCode(postalCode: String): Response<AirQualityData>

    @GET("/latest/by-lat-lng")
    suspend fun getCurrentByCity(city: String): Response<AirQualityData>
}