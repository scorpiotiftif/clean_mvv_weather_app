package fr.francoisgaucher.data

import com.google.gson.Gson
import fr.francoisgaucher.RessourceHelper.Companion.getFileInputStreamFromJsonAsset
import fr.francoisgaucher.RessourceHelper.Companion.readContentFromJsonAsset
import fr.francoisgaucher.data.repositories.modal.AirQualityData
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AirQualityUnitTest {
    val gson = Gson()

    @Test
    fun asset_foundable() {
        val air_quality_result = getFileInputStreamFromJsonAsset("meteo_air_quality_latest_latlng_200.json")
        assertNotNull(air_quality_result)
    }

    @Test
    fun `test that the convertion is OK from JSON response AND convert AirQualityData TO AirQuality`() {
        val air_quality : String? = readContentFromJsonAsset("meteo_air_quality_latest_latlng_200.json")
        println(air_quality)
        assertNotNull(air_quality)
        val airQualityData = gson.fromJson(air_quality, AirQualityData::class.java)
        assertNotNull(airQualityData)
        val airQuality = airQualityData.convertTo()
        assertNotNull(airQuality)
        assertTrue(airQuality.isNotEmpty())
    }
}