package ru.iw.invsetwalet.repository

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

    override fun saveAccount(account: Account) {
        if (account.id == 0) dao.insertAccount(account.toAccountEntity())
        else dao.updateAccount(
            account.id,
            account.title,
            account.description,
            account.type,
            account.currency,
            account.note
        )
    }

    override fun add(account: Account) {
        dao.insertAccount(account.toAccountEntity())
    }

    override fun removeAccount(id: Int) {
        dao.removeAccountById(id)
    }

    override fun getAccountFromDataBase(id: Int): Account {
        return dao.getAccount(id).toAccount()
    }

}