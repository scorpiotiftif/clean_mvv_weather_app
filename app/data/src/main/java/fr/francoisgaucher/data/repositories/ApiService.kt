package fr.francoisgaucher.data.repositories

import androidx.annotation.CallSuper
import fr.francoisgaucher.domain.api.ApiException
import fr.francoisgaucher.domain.api.Http

open class ApiService : BaseApiService() {

    override fun parseCustomError(responseMessage: String, responseCode: Int, errorBodyJson: String): ApiException {
        return mapApiThrowable(responseMessage, responseCode)
    }

    @CallSuper
    protected open fun mapApiThrowable(message: String, code: Int): ApiException {
        return when {
            code > 499 -> {
                Http.ServerException
            }
            code in 300..399 -> {
                Http.TokenException
            }
            else -> {
                Http.ProxyException
            }
        }
    }
}