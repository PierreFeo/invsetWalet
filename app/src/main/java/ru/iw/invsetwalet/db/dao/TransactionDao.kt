package ru.iw.invsetwalet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.iw.invsetwalet.data.AccountWithTransaction
import ru.iw.invsetwalet.db.entity.TransactionsEntity

@Dao
interface TransactionsDao {

    @Insert
    fun addTransaction(transaction: TransactionsEntity)


    @Query("SELECT * FROM currency_transactions_table, ACCOUNTS_TABLE WHERE account_id =:accountId ")
    fun getTransaction(accountId: Int): LiveData<List<TransactionsEntity>>


}