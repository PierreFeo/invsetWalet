package ru.iw.invsetwalet.data

data class Transactions(
    val id: Int,
    val accountId: Int,
    val sum: Int,
    val type: String,
    val ratesBuy: Double,
    val createDate: Double
)