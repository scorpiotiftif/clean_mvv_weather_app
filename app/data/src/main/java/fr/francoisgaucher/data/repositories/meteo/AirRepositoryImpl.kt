package fr.francoisgaucher.data.repositories.meteo

import com.google.gson.Gson
import fr.francoisgaucher.data.repositories.ApiService
import fr.francoisgaucher.data.repositories.HttpInterceptor
import fr.francoisgaucher.data.repositories.RetrofitManager
import fr.francoisgaucher.data.repositories.modal.AirQualityData
import fr.francoisgaucher.domain.api.ApiException
import fr.francoisgaucher.domain.api.Http
import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.repositories.MeteoAirRepository
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class AirRepositoryImpl : MeteoAirRepository, ApiService() {

    private val meteoAirService: AirMeteoService

    // ####################################################################
    // ####################################################################
    // ############## https://api-dashboard.getambee.com/ #################
    // ####################################################################
    // ####################################################################

    init {
        val build = RetrofitManager.retrofitBuilder
            .baseUrl("https://api.ambeedata.com")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(OkHttpClient.Builder().addInterceptor(HttpInterceptor()).build())
            .build()
        meteoAirService = build.create(AirMeteoService::class.java)
    }

    override suspend fun getCurrentByCoordonate(coordonate: Pair<Double, Double>): Result<AirQuality> {
        val result: Result<AirQualityData> = apiCall(call = { meteoAirService.getCurrentByCoordonate(lat = coordonate.first, lng = coordonate.second) })
        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(result.data.convertTo()[0])
        }
    }

    override suspend fun getCurrentByPostalCode(postalCode: String): Result<AirQuality> {
        throw RuntimeException()
        //return apiCall(call = { meteoAirService.getCurrentByPostalCode(postalCode = postalCode) })
    }

    override suspend fun getCurrentByCity(city: String): Result<AirQuality> {
        throw RuntimeException()
        //return apiCall(call = { meteoAirService.getCurrentByCity(city = city) })
    }

    override fun mapApiThrowable(message: String, code: Int): ApiException {
        return when (code) {
            1000 -> Http.ProxyException
            else -> super.mapApiThrowable(message, code)
        }
    }

    override fun parseCustomError(responseMessage: String, responseCode: Int, errorBodyJson: String): ApiException {
        return mapApiThrowable(responseMessage, responseCode)
    }
}