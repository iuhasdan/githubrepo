package com.example.githubrepo.network

import com.example.githubrepo.utilities.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIProvider {
    companion object {
        fun buildRetrofitClient(): Retrofit {

            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

            val client: OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}