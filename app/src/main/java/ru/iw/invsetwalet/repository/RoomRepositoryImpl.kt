package ru.iw.invsetwalet.repository


import android.app.Application
import android.util.Log
import androidx.lifecycle.map
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.AccountWithTransaction
import ru.iw.invsetwalet.data.Transactions
import ru.iw.invsetwalet.db.AppDb
import ru.iw.invsetwalet.db.entity.AccountEntity
import ru.iw.invsetwalet.db.entity.toAccount
import ru.iw.invsetwalet.db.entity.toAccountEntity
import ru.iw.invsetwalet.db.entity.toTransactionsEntity

class RoomRepositoryImpl(
    // private val dao: AccountDao,
    private val application: Application
) : RoomRepository {

    private val dao = AppDb.getInstance(application).accountDao
    private val transactionsDao = AppDb.getInstance(application).transactionDao


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
                val sum = 10
                val rate = 85.0
                val itog = sum * rate
                transactionsDao.addTransaction(
                    Transactions(
                        0,
                        2,
                        sum,
                        "Пополение",
                        rate,
                        itog
                    ).toTransactionsEntity()
                )
            }

            override fun removeAccount(id: Int) {


                dao.removeAccountById(id)
            }

            override fun getAccountFromDataBase(id: Int): Account {
                return dao.getAccount(id).toAccount()
            }


        }