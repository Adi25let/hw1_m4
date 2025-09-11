package com.example.hw1_m4.domain.presenter

import com.example.hw1_m4.data.model.Account

interface AccountContracts {
    interface View{
        fun showAccounts(list: List<Account>)
    }
    interface Presenter{
        fun loadAccounts()
        fun addAccount(account: Account)
    }
}