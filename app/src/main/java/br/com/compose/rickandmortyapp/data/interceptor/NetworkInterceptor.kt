package br.com.compose.rickandmortyapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        if (response.isSuccessful) {
            return response
        } else {
            when {
                response.code in 400..499 -> {
                    throw IllegalAccessException("Illegal access")
                }
                response.code >= 500 -> {
                    throw Exception("Internal server error")
                }
                else -> throw Exception("Unknown Error")
            }
        }
    }
}