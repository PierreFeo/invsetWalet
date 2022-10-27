package ru.iw.invsetwalet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.iw.invsetwalet.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts_table")
    fun getAll(): LiveData<List<AccountEntity>>

    @Insert
    fun insert(account: AccountEntity)

    @Query("DELETE FROM accounts_table WHERE idAccount = :id")
    fun removeAccountById(id: Int)
}