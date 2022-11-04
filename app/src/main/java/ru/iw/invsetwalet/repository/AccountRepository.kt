package ru.iw.invsetwalet.repository

import androidx.lifecycle.LiveData
import ru.iw.invsetwalet.data.Account

interface AccountRepository {
    fun getAll(): LiveData<List<Account>>
    fun saveAccount(account: Account)
    fun add(account: Account)
    fun removeAccount(id: Int)
    fun getAccountFromDataBase(id: Int): Account
}