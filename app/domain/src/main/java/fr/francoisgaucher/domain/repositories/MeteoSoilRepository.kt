package fr.francoisgaucher.domain.repositories

interface MeteoSoilRepository {
    suspend fun getRandomNumber(): Int
    suspend fun getRandomText(): String
}