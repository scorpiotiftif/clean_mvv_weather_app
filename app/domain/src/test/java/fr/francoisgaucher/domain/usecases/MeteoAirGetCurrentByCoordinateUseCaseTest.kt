package fr.francoisgaucher.domain.usecases

import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.repositories.MeteoAirRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any

@RunWith(MockitoJUnitRunner::class)
class MeteoAirGetCurrentByCoordinateUseCaseTest {

    @Mock
    lateinit var meteoAirRepository: MeteoAirRepository

    var airQualityResult = AirQuality()

    @Before
    fun setUp() {
        runBlocking {
            Mockito.`when`(meteoAirRepository.getCurrentByCoordonate(any())).thenReturn(Result.Success(airQualityResult))
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    operator fun invoke() = runBlocking{
        val meteoAirGetCurrentByCoordinateUseCase = MeteoAirGetCurrentByCoordinateUseCase(meteoAirRepository)
        val result = meteoAirGetCurrentByCoordinateUseCase(param = Pair(1.0,1.0))
        val success = Result.Success(airQualityResult)
        Assert.assertNotNull(result)
        Assert.assertNotNull(success)
        Assert.assertEquals(success, result)
    }
}
