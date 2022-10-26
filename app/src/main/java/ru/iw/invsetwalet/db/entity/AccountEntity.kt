package ru.iw.invsetwalet.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.iw.invsetwalet.data.Account


@Entity(tableName = "accounts_table")
data class AccountEntity(
    @ColumnInfo(name = "idAccount")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "titleAccount")
    val title: String,
    @ColumnInfo(name = "descriptionAccount")
    val description: String,
    @ColumnInfo(name = "typeAccount")
    val type: String,
)

fun AccountEntity.toAccount() = Account(
    id = id,
    title = title,
    description = description,
    type = type
)

fun Account.toAccountEntity() = AccountEntity(
    id = id,
    title = title,
    description = description,
    type = type
)
