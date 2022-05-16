package fr.francoisgaucher.domain.usecases

sealed class UseCase<out T> {
    data class Error(val cause: Cause):UseCase<Nothing>() {
        sealed class Cause {
            object FunctionalError : Cause()
            object NetworkError : Cause()
            object TechnicalError : Cause()
        }
    }

    data class Success<out T : Any>(val data: T) : UseCase<T>()
}
