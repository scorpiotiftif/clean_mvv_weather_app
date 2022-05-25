package fr.francoisgaucher.data.repositories.meteo

import androidx.test.platform.app.InstrumentationRegistry
import fr.francoisgaucher.data.repositories.modal.AirQualityData
import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
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
import org.robolectric.RobolectricTestRunner
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
class AirRepositoryMockedImplTest {

    lateinit var airRepositoryImpl: AirRepositoryMockedImpl

    @Before
    fun setUp() {
        airRepositoryImpl = AirRepositoryMockedImpl(InstrumentationRegistry.getInstrumentation().context)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCurrentByCoordonateMocked(): Unit = runBlocking {

        val currentByCoordonate: Result<AirQuality> =
            airRepositoryImpl.getCurrentByCoordonate(Pair(1.0, 1.0))
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
