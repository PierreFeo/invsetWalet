package ru.iw.invsetwalet.data

data class Transactions(
    val id: Int,
    val accountId: Int,
    val amountTransact: Double,
    val typeTransact: String,
    val ratesBuy: Double,
    val amountRub: Double,
    val createDate: String
)