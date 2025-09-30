package com.example.hw1_m4
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1_m4.data.model.Account
import com.example.hw1_m4.data.network.AccountsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val accountsApi: AccountsApi
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account: LiveData<Account> = _account

    fun loadAccount(id: String) {
        accountsApi.fetchAccountById(id).enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if (response.isSuccessful) {
                    response.body()?.let { _account.value = it }
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
            }
        })
    }

    fun deleteAccount(id: String) {
        accountsApi.deleteAccount(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {}
        })
    }

    fun updateAccount(account: Account) {
        accountsApi.updateFullyAccount(account.id!!, account).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {}
            override fun onFailure(call: Call<Unit>, t: Throwable) {}
        })
    }
}
