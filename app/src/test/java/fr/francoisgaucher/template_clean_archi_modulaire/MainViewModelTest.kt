package fr.francoisgaucher.template_clean_archi_modulaire

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.usecases.MeteoAirGetCurrentByCoordinateUseCase
import fr.francoisgaucher.domain.usecases.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {


    private lateinit var viewModel: MainViewModel
    @get:Rule
    val coroutineScope =  MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()//For LiveData

    @Mock
    private lateinit var observer: Observer<State>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<State>

    @Before
    fun setup() {
    }

    @Test
    fun refreshAirQuality() = runTest {
        viewModel = MainViewModel(coroutineScope.testDispatcher)
        viewModel.coroutineScope = this
        val mock = Mockito.mock(MeteoAirGetCurrentByCoordinateUseCase::class.java)
        val success = State.Succes(AirQuality())
        val useCaseSuccess = UseCase.Success(AirQuality())
        Mockito.`when`(mock.invoke(any())).thenReturn(useCaseSuccess)
        viewModel.meteoAirCompletetUseCase=mock
        viewModel.airQualityLiveData.observeForever(observer)

        viewModel.refreshAirQuality()
        Mockito.verify(observer).onChanged(argumentCaptor.capture())

        val allValues = argumentCaptor.allValues
        Assert.assertEquals(success, allValues[0])

        //assert(State.Succes(AirQuality()) == viewModel.airQualityLiveData.value)
    }
}
