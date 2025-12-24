package com.eunbi.character.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface ApiService {

    companion object {
        private var okHttpClientInstance = OkHttpClient()
        private val Authorization = "kkhYnjccukSoKfEl0OryJLOE4moPa-E3BihnS8kwpp8"

        fun build(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val builder = okHttpClientInstance.newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)

            builder.apply {
                addInterceptor { chain ->
                    chain.proceed(
                        chain.request()
                            .newBuilder()
                            .apply {
                                header("Authorization", "Client-ID $Authorization")
                            }
                            .build()
                    )
                }
            }

            return builder.retryOnConnectionFailure(true).build()
        }
    }
}