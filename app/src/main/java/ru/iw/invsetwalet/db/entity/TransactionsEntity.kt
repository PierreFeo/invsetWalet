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
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "sum")
    val sum: Int,
    @ColumnInfo(name = "rates_buy")
    val ratesBuy: Double,
    @ColumnInfo(name = "sum_rub")
    val createDate: Double
)

fun TransactionsEntity.toTransactions() = Transactions(
    id = id,
    accountId = accountId,
    type = type,
    sum = sum,
    ratesBuy = ratesBuy,
    createDate = createDate
)

fun Transactions.toTransactionsEntity() = TransactionsEntity(
    id = id,
    accountId = accountId,
      type = type,
    sum = sum,
    ratesBuy = ratesBuy,
    createDate = createDate
)