package ru.iw.invsetwalet.adapter

import ru.iw.invsetwalet.data.Account

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
    fun getAccountFromDataBase(id: Int):Account
}