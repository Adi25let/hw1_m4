package com.example.hw1_m4.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1_m4.data.model.Account
import com.example.hw1_m4.data.model.AccountState
import com.example.hw1_m4.data.network.AccountsApi
import com.example.hw1_m4.di.NetworkModule
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountsApi: AccountsApi
): ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

   fun loadAccounts() {
        accountsApi.fetchAccounts().enqueue(object: Callback<List<Account>> {
            override fun onResponse(
                call: Call<List<Account>?>,
                response: Response<List<Account>?>
            ) {
                if (response.isSuccessful) {
                    val result = response.body() ?: emptyList()
                    _accounts.value = result
                }else{
                    _errorMessage.value = "${response.code()} ${response.message()}"
                }
            }

            override fun onFailure(
                call: Call<List<Account>?>,
                t: Throwable) {

            }
        })
    }

    fun addAccount(account: Account) {
        accountsApi.createAccount(account).enqueue(object: Callback<Account> {
            override fun onResponse(
                call: Call<Account?>,
                response: Response<Account?>
            ) {
                if (response.isSuccessful)  loadAccounts()
            }

            override fun onFailure(
                call: Call<Account?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })
    }

    fun updateFullyAccount(account: Account) {
        accountsApi.updateFullyAccount(account.id!!, account).enqueue(object  :
            Callback<Unit> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                if (response.isSuccessful) {
                    _successMessage.value = "Состояние счета изменено!"
                    loadAccounts()
                }
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {

            }
        })
    }

    fun updateStateAccount(
        accountId: String,
        accountState: AccountState
    ) {
        accountsApi.updateAccountState(accountId, accountState).enqueue(object  :
            Callback<Unit> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                if (response.isSuccessful)  loadAccounts()
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {

            }
        })
    }

    fun deleteAccount(accountId: String) {
        accountsApi.deleteAccount(accountId).enqueue(object  : Callback<Unit> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                if (response.isSuccessful)  loadAccounts()
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {

            }
        })
    }
}