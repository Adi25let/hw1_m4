package com.example.hw1_m4.di

import com.example.hw1_m4.data.network.AccountsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun loginInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

    @Provides
    fun httpClient(loginInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()

    @Provides
    fun retrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://68c285fdf9928dbf33eeafd2.mockapi.io/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun accountsApi(retrofit: Retrofit): AccountsApi = retrofit.create(AccountsApi::class.java)
}