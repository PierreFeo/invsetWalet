package ru.iw.invsetwalet.data

import android.content.Context
import ru.iw.invsetwalet.R

enum class TypeAccount {
    CASH,
    BANK_DEPOSIT,
    PROPERTY,
    AUTOMOBILE,
    OTHER;

    fun getText(context: Context) = when (this) {
        CASH -> context.getString(R.string.currency)
        BANK_DEPOSIT -> context.getString(R.string.BANK_DEPOSIT)
        PROPERTY -> context.getString(R.string.PROPERTY)
        AUTOMOBILE -> context.getString(R.string.AUTOMOBILE)
        OTHER -> context.getString(R.string.OTHER)

    }
}
