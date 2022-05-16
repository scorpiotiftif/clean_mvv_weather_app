package fr.francoisgaucher.domain.repositories

interface MeteoPollenRepository {
    suspend fun getRandomNumber(): Int
    suspend fun getRandomText(): String
}