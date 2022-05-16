package fr.francoisgaucher.domain.api

sealed interface ApiException

sealed class Network:ApiException{
    object NoConnectionException : Network(),ApiException
    object UrlNotFoundConnectionException : Network(),ApiException
}

sealed class Http:ApiException{
    object ProxyException : Http(),ApiException
    object TokenException : Http(),ApiException
    object ServerException : Http(),ApiException
    data class EmptyBodyException(val message: String) : Http(),ApiException
    data class BodyMalFormattedException(val message:String) : Http(),ApiException
}