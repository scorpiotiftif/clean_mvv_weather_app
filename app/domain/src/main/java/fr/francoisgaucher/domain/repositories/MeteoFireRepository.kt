package fr.francoisgaucher.domain.repositories

interface MeteoFireRepository {
    suspend fun getRandomNumber(): Int
    suspend fun getRandomText(): String
}