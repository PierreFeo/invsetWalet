package ru.iw.invsetwalet.data

import androidx.room.Embedded
import androidx.room.Relation
import ru.iw.invsetwalet.db.entity.AccountEntity
import ru.iw.invsetwalet.db.entity.TransactionsEntity

data class AccountWithTransaction(
    @Embedded
    val account: AccountEntity,

    @Relation(parentColumn = "id", entity = TransactionsEntity::class, entityColumn = "account_id")
    val transactions: List<TransactionsEntity>
)