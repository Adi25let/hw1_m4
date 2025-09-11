package com.example.hw1_m4.data.network

import retrofit2.Call
import com.example.hw1_m4.data.model.Account
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountsApi {

    @GET("accounts")
    fun fetchAccounts(): Call<List<Account>>

    @POST("accounts")
    fun createAccount(@Body account: Account): Call<Account>

}