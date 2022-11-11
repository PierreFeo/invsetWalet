package ru.iw.invsetwalet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.TypeAccount
import ru.iw.invsetwalet.databinding.CardAccountFragmentBinding
import ru.iw.invsetwalet.ui.NewAccountFragment
import kotlin.math.roundToInt

internal class AccountAdapter(
    private val listener: AccountInteractionListener
) : ListAdapter<Account, AccountViewHolder>(DiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view =
            CardAccountFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(listener, view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))


    }

    private object DiffCallBack : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(
            oldItem: Account,
            newItem: Account
        ): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(
            oldItem: Account,
            newItem: Account
        ): Boolean {
            return oldItem == newItem
        }

    }
}


class AccountViewHolder(
    private val listener: AccountInteractionListener,
    private val binding: CardAccountFragmentBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(account: Account) = with(binding) {
        titleAccount.text = account.title
        descriptionAccount.text = account.description
        sumAccount.formatDisplaySymbolCurrency(account)
        percentAccount.formatDisplayResultPercent(account)


        root.setOnLongClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.popup)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.removeButtonMenu -> {
                            listener.onRemoveClicked(account.id)
                            //TODO Confirmation remove
                            true
                        }
                        R.id.changeButtonMenu -> {
                            listener.onEditClicked(account)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }.show()
            true
        }
    }

    private fun TextView.formatDisplaySymbolCurrency(account: Account) {
        text = when (account.currency) {
            NewAccountFragment.USD_TYPE -> "${roundDouble(account.total)} $"
            NewAccountFragment.RUB_TYPE -> "${roundDouble(account.total)} ₽"
            NewAccountFragment.CHY_TYPE -> "${roundDouble(account.total)} ¥"
            NewAccountFragment.EUR_TYPE -> "${roundDouble(account.total)} €"
            else -> "${roundDouble(account.total)} "
        }
    }

    private fun TextView.formatDisplayResultPercent(account: Account) {
        text = if (TypeAccount.CURRENCY.getText(context) == account.type) {
            Log.d("enum",TypeAccount.CURRENCY.getText(context))
            "Валюта"
        } else {
            Log.d("enum2",TypeAccount.CURRENCY.getText(context))
            "Не валюта"
        }
    }

    private fun roundDouble(double: Double): Double {
        return (double * 100).roundToInt() / 100.0
    }





    companion object {
        const val TEMPORARY_CURRENT_RATE = 61.28
    }
}


