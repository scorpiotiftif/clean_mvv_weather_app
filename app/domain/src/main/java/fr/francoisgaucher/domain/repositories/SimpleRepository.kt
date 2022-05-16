package fr.francoisgaucher.domain.repositories

interface SimpleRepository {
    suspend fun getRandomNumber(): Int
    suspend fun getRandomText(): String
}