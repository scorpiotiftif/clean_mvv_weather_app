package fr.francoisgaucher.domain.usecases

import fr.francoisgaucher.domain.repositories.SimpleRepository

class SimpleGetIntUseCase(private val booksRepository: SimpleRepository) {
    suspend operator fun invoke(){
        booksRepository.getRandomNumber()
    }
}

class SimpleGetTextUseCase(private val booksRepository: SimpleRepository) {
    suspend operator fun invoke() = booksRepository.getRandomText()
}