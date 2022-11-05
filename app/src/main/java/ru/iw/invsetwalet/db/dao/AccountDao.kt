package ru.iw.invsetwalet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.iw.invsetwalet.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts_table")
    fun getAll(): LiveData<List<AccountEntity>>

    @Insert
    fun insertAccount(account: AccountEntity)

    @Query("UPDATE accounts_table SET titleAccount =:title, descriptionAccount =:description,typeAccount =:type, currencyAccount =:currency, noteAccount =:note WHERE idAccount IN (:id)")
    fun updateAccount(
        id: Int,
        title: String,
        description: String,
        type: String,
        currency: String,
        note: String,
    )

    @Query("DELETE FROM accounts_table WHERE idAccount = :id")
    fun removeAccountById(id: Int)


    @Query("SELECT * FROM accounts_table WHERE idAccount = :id")
    fun getAccount(id: Int): AccountEntity

}