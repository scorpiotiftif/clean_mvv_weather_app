package fr.francoisgaucher.data.repositories

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("x-api-key", "bf8fbed461d60b2b82c95302c152fb3b299a073617ae0dd21b7c513d77d630a0")
                .addHeader("Content-type", "application/json")
                .removeHeader("User-Agent")
                .build()
        )
    }
}