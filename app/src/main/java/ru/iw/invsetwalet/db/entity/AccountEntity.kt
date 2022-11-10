package ru.iw.invsetwalet.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.iw.invsetwalet.data.Account


@Entity(tableName = "accounts_table")
data class AccountEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "total")
    val total: Double,
    @ColumnInfo(name = "total_rub")
    val totalInRub: Double,
    @ColumnInfo(name = "createDate")
    val createDate: String,
    @ColumnInfo(name = "is_removed")
    val isRemoved: Boolean
)

fun AccountEntity.toAccount() = Account(
    id = id,
    title = title,
    description = description,
    type = type,
    currency = currency,
    note = note,
    total = total,
    totalInRub = totalInRub,
    createDate = createDate,
    isRemoved = isRemoved
)

fun Account.toAccountEntity() = AccountEntity(
    id = id,
    title = title,
    description = description,
    type = type,
    currency = currency,
    note = note,
    total = total,
    totalInRub = totalInRub,
    createDate = createDate,
    isRemoved = isRemoved
)
