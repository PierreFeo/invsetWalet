package ru.iw.invsetwalet.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.iw.invsetwalet.data.Transactions

@Entity(
    tableName = "currency_transactions_table",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class TransactionsEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "account_id")
    val accountId: Int,
    @ColumnInfo(name = "type_transaction")
    val typeTransact: String,
    @ColumnInfo(name = " amount_transaction")
    val amountTransact: Double,
    @ColumnInfo(name = "rates_buy")
    val ratesBuy: Double,
    @ColumnInfo(name = "create_rub")
    val createDate: String
)

fun TransactionsEntity.toTransactions() = Transactions(
    id = id,
    accountId = accountId,
    typeTransact = typeTransact,
    amountTransact = amountTransact,
    ratesBuy = ratesBuy,
    createDate = createDate
)

fun Transactions.toTransactionsEntity() = TransactionsEntity(
    id = id,
    accountId = accountId,
    typeTransact = typeTransact,
    amountTransact = amountTransact,
    ratesBuy = ratesBuy,
    createDate = createDate
)