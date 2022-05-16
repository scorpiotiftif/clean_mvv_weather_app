package fr.francoisgaucher.data.repositories

import android.util.Log
import com.google.gson.Gson
import fr.francoisgaucher.data.repositories.modal.AirQualityData
import fr.francoisgaucher.domain.api.ApiException
import fr.francoisgaucher.domain.api.Http
import fr.francoisgaucher.domain.api.Network
import fr.francoisgaucher.domain.api.Result
import retrofit2.Response


abstract class BaseApiService {

    protected abstract fun parseCustomError(responseMessage: String, responseCode: Int, errorBodyJson: String): ApiException


    protected fun mapNetworkThrowable(throwable: Throwable): Network = Network.NoConnectionException

    protected fun mapHttpThrowable(throwable: Throwable, code: Int, message: String): Http {
        //InvalidGrantException, ForbiddenException, ServerException, ...
        return Http.ServerException
    }

    // This method allow to call an apiService method and handle good OR bad response to encapsulate them onto our application model
    protected suspend inline fun <reified T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (e: Exception) {
            Log.e("BaseApiService", e.message?:"" )
            // If call crash, we can handle BAD State and return the correct one to our domain layer
            return Result.Error(mapNetworkThrowable(e))
        }

        return if (!response.isSuccessful) {
            Log.e("BaseApiService", response.message() )
            val errorBody = response.errorBody()
            Result.Error(parseCustomError(responseMessage = response.message(), responseCode = response.code(), errorBodyJson = errorBody?.string() ?: ""))
        } else {
            if (response.body() == null) {
                Result.Error(Http.EmptyBodyException("response.body() can't be null"))
            } else {
                Result.Success(response.body()!!)
            }
        }
    }
}