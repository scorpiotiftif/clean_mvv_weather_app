package fr.francoisgaucher.data.repositories

import fr.francoisgaucher.domain.repositories.SimpleRepository
import kotlin.random.Random

class SimpleRepositoryImpl : SimpleRepository {

    val datas : Array<String> = arrayOf("Serge","Oskar","Pierre", "Mariam","Eduardo","Sefa","Lanzo","Elisa","Caetano","Bathilde","Malina","Marine")

    override suspend fun getRandomNumber(): Int {
        return Random.nextInt()
    }

    override suspend fun getRandomText(): String {
        return datas.random()
    }
}