package ru.iw.invsetwalet.repository

import androidx.lifecycle.LiveData
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.Transactions

interface RoomRepository {
    fun getAll(): LiveData<List<Account>>
    fun saveAccount(account: Account)
    fun add(account: Account)
    fun removeAccount(id: Int)
    fun getAccountFromDataBase(id: Int): Account
    fun saveTransaction(transactions: Transactions)
}