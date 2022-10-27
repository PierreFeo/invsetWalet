package ru.iw.invsetwalet.adapter

import ru.iw.invsetwalet.data.Account

interface AccountInteractionListener {
    fun onAddClicked()
    fun onRemoveClicked(id: Int)
}