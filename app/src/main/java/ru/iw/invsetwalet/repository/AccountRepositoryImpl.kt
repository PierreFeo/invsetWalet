package ru.iw.invsetwalet.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.db.dao.AccountDao
import ru.iw.invsetwalet.db.entity.AccountEntity
import ru.iw.invsetwalet.db.entity.toAccount
import ru.iw.invsetwalet.db.entity.toAccountEntity

class AccountRepositoryImpl(
    private val dao: AccountDao
) : AccountRepository {

    override fun getAll() = dao.getAll().map { list: List<AccountEntity> ->
        list.map { AccountEntity -> AccountEntity.toAccount() }
    }

    override fun add(account: Account) {
        dao.insert(account.toAccountEntity())
    }

}