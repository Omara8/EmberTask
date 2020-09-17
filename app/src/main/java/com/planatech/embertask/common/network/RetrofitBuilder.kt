package com.planatech.embertask.common.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        fun getRetrofit(): Retrofit {
            return retrofit ?: synchronized(this) {
                build()
            }
        }

        private const val baseUrl: String = "http://newsapi.org/v2/"

        private fun build(): Retrofit {
            val httpClient = getHttpClientBuilder()
            return getRetroFit(httpClient)
        }

        private fun getRetroFit(httpClient: OkHttpClient.Builder): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create()).client(httpClient.build())
                .build()
        }

        private fun getHttpClientBuilder(): OkHttpClient.Builder {
            val httpClient = OkHttpClient().newBuilder()
            httpClient.readTimeout(60, TimeUnit.SECONDS)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)

            return httpClient
        }

    }

}