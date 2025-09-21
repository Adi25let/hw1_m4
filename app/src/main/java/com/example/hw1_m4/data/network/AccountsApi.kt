package com.example.hw1_m4.data.network

import retrofit2.Call
import com.example.hw1_m4.data.model.Account
import com.example.hw1_m4.data.model.AccountState
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface  AccountsApi {

    @GET("accounts")
    fun fetchAccounts(): Call<List<Account>>

    @POST("accounts")
    fun createAccount(@Body account: Account): Call<Account>

    @PUT("accounts/{id}")
    fun updateFullyAccount(
        @Path("id") accountId: String,
        @Body account: Account
    ): Call<Unit>

    @PATCH("accounts/{id}")
    fun updateAccountState(
        @Path("id") accountId: String,
        @Body accountState: AccountState
    ): Call<Unit>

    @DELETE("accounts/{id}")
    fun deleteAccount(
        @Path("id") accountId: String
    ):Call<Unit>
}