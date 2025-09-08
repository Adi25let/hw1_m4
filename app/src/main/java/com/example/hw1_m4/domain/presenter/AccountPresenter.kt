package com.example.hw1_m4.domain.presenter

import com.example.hw1_m4.data.model.Account

class AccountPresenter(val view: AccountContracts.View): AccountContracts.Presenter {
    override fun loadAccounts() {
        val testMockAccounts = listOf(Account(
            id = "1",
            name = "o! bank",
            currency = "USD",
            balance = 1000,
           ),
            Account(
                id = "2",
                name = "m-bank",
                currency = "KGS",
                balance = 2000,
            ),
            Account(
                id = "3",
                name = "a-bank",
                currency = "RUB",
                balance = 3000,
            )
        )
        view.showAccounts(testMockAccounts)
    }
}