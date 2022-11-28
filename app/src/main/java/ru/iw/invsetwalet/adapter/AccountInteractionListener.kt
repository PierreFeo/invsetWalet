package ru.iw.invsetwalet.adapter


import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.Transactions

interface AccountInteractionListener {
    fun onSaveClicked(
        title: String,
        description: String,
        type: String,
        currency: String,
        note: String,
        date: String
    )

    fun onAddClicked()
    fun onRemoveClicked(id: Int)
    fun onEditClicked(account: Account)
    fun getAccountFromDataBase(id: Int): Account
    fun onAddPaymentClicked(boolean: Boolean)
    fun onAccountClicked(account: Account)
    fun onSaveTransaction(
        accountId: Int,
        amountTransact: Double,
        typeTransact: String,
        ratesBuy: Double?,
        result:Double,
        createDate: String
    )
}