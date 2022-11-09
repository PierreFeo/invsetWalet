package ru.iw.invsetwalet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.iw.invsetwalet.data.AccountWithTransaction
import ru.iw.invsetwalet.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts_table")
    fun getAll(): LiveData<List<AccountEntity>>

    @Insert
    fun insertAccount(account: AccountEntity)

    @Query("UPDATE accounts_table SET title =:title, description =:description,type =:type, currency =:currency, note =:note WHERE id IN (:id)")
    fun updateAccount(
        id: Int,
        title: String,
        description: String,
        type: String,
        currency: String,
        note: String,
    )

    @Query("DELETE FROM accounts_table WHERE id = :id")
    fun removeAccountById(id: Int)


    @Query("SELECT * FROM accounts_table WHERE id = :id")
    fun getAccount(id: Int): AccountEntity


    @Query("SELECT * FROM accounts_table")
    fun loadAccounts(): LiveData<List<AccountWithTransaction>>

//    @Transaction
//    @Query("SELECT * FROM accounts_table")
//    fun getAccount():AccountWithTransaction

}