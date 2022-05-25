package fr.francoisgaucher.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality
import fr.francoisgaucher.domain.repositories.MeteoAirRepository
import javax.inject.Inject

abstract class MeteoAirSimpleUseCase(protected val meteoAirRepository: MeteoAirRepository) {
    abstract suspend operator fun invoke()
}

abstract class MeteoAirParamUseCase<T>(protected val meteoAirRepository: MeteoAirRepository) {
    abstract suspend operator fun invoke(param: T)
}

abstract class MeteoAirResultUseCase<K>(protected val meteoAirRepository: MeteoAirRepository) {
    abstract suspend operator fun invoke(): K
}

abstract class MeteoAirCompletetUseCase<T, K>(protected val meteoAirRepository: MeteoAirRepository) {
    abstract suspend operator fun invoke(param: T): K
}

@ViewModelScoped
class MeteoAirGetCurrentByCoordinateUseCase @Inject constructor(meteoAirRepository: MeteoAirRepository) : MeteoAirCompletetUseCase<Pair<Double, Double>, UseCase<AirQuality>>(meteoAirRepository) {
    override suspend operator fun invoke(param: Pair<Double, Double>): UseCase<AirQuality> {
        return meteoAirRepository.getCurrentByCoordonate(param).let { result ->
            when (result) {
                is Result.Error -> UseCase.Error(UseCase.Error.Cause.NetworkError)
                is Result.Success -> UseCase.Success(result.data)
            }
        }
    }
}

class MeteoAirGetCurrentByCityUseCase(meteoAirRepository: MeteoAirRepository) : MeteoAirCompletetUseCase<String, Result<AirQuality>>(meteoAirRepository) {
    override suspend operator fun invoke(param: String) = meteoAirRepository.getCurrentByCity(param)
}
