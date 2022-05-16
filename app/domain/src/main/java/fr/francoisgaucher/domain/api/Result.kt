package fr.francoisgaucher.domain.api

sealed class Result<out T> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: ApiException) : Result<Nothing>()
}
