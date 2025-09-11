package com.example.hw1_m4.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

private val loginInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://68c285fdf9928dbf33eeafd2.mockapi.io/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val accountsApi: AccountsApi = retrofit.create(AccountsApi::class.java)
}