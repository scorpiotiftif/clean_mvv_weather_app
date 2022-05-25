package fr.francoisgaucher.data.repositories.meteo

import com.google.gson.Gson
import fr.francoisgaucher.data.RessourceHelper
import fr.francoisgaucher.data.repositories.modal.AirQualityData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AirRepositoryImplTest {

    lateinit var airRepositoryImpl: AirRepositoryImpl

    @Mock
    lateinit var airMeteoService: AirMeteoService

    @Before
    fun setUp() {
        airRepositoryImpl = AirRepositoryImpl()
        airRepositoryImpl.meteoAirService = airMeteoService
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCurrentByCoordonateFromService(): Unit = runBlocking {
        val mock = Mockito.mock(AirQualityData::class.java)
        `when`(
            airMeteoService.getCurrentByCoordonate(
                ArgumentMatchers.anyDouble(),
                ArgumentMatchers.anyDouble()
            )
        ).thenReturn(Response.success(mock))

        val currentByCoordonate = airMeteoService.getCurrentByCoordonate(1.0, 1.0)

        assertNotNull(currentByCoordonate.isSuccessful)
    }

    @Test
    fun getCurrentByCoordonate(): Unit = runBlocking {
        val air_quality: String? =
            RessourceHelper.readContentFromJsonAsset("meteo_air_quality_latest_latlng_200.json")
        println(air_quality)
        assertNotNull(air_quality)
        val airQualityData = Gson().fromJson(air_quality, AirQualityData::class.java)
        assertNotNull(airQualityData)
        `when`(
            airMeteoService.getCurrentByCoordonate(
                ArgumentMatchers.anyDouble(),
                ArgumentMatchers.anyDouble()
            )
        ).thenReturn(Response.success(airQualityData))

        val currentByCoordonate = airRepositoryImpl.getCurrentByCoordonate(Pair(1.0, 1.0))

        assertNotNull(currentByCoordonate)
    }

    @Test
    fun getCurrentByPostalCode() {
        assert(false)
    }

    @Test
    fun getCurrentByCity() {
        assert(false)
    }

    @Test
    fun mapApiThrowable() {
        assert(false)
    }

    @Test
    fun parseCustomError() {
        assert(false)
    }
}
