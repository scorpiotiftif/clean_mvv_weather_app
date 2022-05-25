package fr.francoisgaucher.template_clean_archi_modulaire

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.usecases.MeteoAirGetCurrentByCoordinateUseCase
import fr.francoisgaucher.domain.usecases.UseCase
import fr.francoisgaucher.template_clean_archi_modulaire.di.CoroutineDispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Error : State()
    object Loading : State()
    data class Succes(val airQuality: AirQuality) : State()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @CoroutineDispatcherIO private val currentDispatcher: CoroutineDispatcher) : ViewModel() {

    @VisibleForTesting
    var coroutineScope: CoroutineScope? = null

    init {
        if(coroutineScope == null){
            coroutineScope = viewModelScope
        }
    }

    @Inject
    lateinit var meteoAirCompletetUseCase: MeteoAirGetCurrentByCoordinateUseCase

    // LIVE DATA
    private val airQualityMutableLiveData: MutableLiveData<State> = MutableLiveData()
    val airQualityLiveData: LiveData<State>
        get() = airQualityMutableLiveData

    // DATA
    private var newAirQuality: AirQuality? = null

    fun refreshAirQuality() {
        coroutineScope!!.launch(currentDispatcher) {
            when (val result: UseCase<AirQuality> = meteoAirCompletetUseCase(Pair(12.0, 77.0))) {
                is UseCase.Error -> {
                    when (result.cause) {
                        UseCase.Error.Cause.FunctionalError -> airQualityMutableLiveData.postValue(
                            State.Error
                        )
                        UseCase.Error.Cause.NetworkError -> airQualityMutableLiveData.postValue(
                            State.Error
                        )
                        UseCase.Error.Cause.TechnicalError -> airQualityMutableLiveData.postValue(
                            State.Error
                        )
                    }
                }
                is UseCase.Success -> {
                    newAirQuality = result.data
                    airQualityMutableLiveData.postValue(State.Succes(newAirQuality!!))
                }
            }
        }
    }

    private fun handleError(error: Result.Error) {
        when (error.exception) {

        }
    }
}
