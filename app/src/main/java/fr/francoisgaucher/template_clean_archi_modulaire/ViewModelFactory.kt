package fr.francoisgaucher.template_clean_archi_modulaire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.francoisgaucher.domain.usecases.MeteoAirGetCurrentByCoordinateUseCase
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModelFactory(private val coroutineDispatcher: CoroutineDispatcher, private val useCase: MeteoAirGetCurrentByCoordinateUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(coroutineDispatcher, useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}