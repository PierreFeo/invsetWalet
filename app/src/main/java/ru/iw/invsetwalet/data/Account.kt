package ru.iw.invsetwalet.data

import java.text.FieldPosition

data class Account(
    val id: Int,
    val title: String,
    val description: String,
    val type: String,
    val currency: String,
    val note: String,
    val createDate: String
)