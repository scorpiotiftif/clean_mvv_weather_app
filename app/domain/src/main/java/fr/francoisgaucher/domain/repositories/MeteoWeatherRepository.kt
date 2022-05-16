package fr.francoisgaucher.domain.repositories

interface MeteoWeatherRepository {
    suspend fun getRandomNumber(): Int
    suspend fun getRandomText(): String
}