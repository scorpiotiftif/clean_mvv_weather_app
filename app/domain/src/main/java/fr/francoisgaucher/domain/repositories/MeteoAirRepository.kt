package fr.francoisgaucher.domain.repositories

import fr.francoisgaucher.domain.api.Result
import fr.francoisgaucher.domain.model.AirQuality

interface MeteoAirRepository {
    suspend fun getCurrentByCoordonate(coordonate: Pair<Double, Double>): Result<AirQuality>
    suspend fun getCurrentByPostalCode(postalCode: String): Result<AirQuality>
    suspend fun getCurrentByCity(city: String): Result<AirQuality>
}