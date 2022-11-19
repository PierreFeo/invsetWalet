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


        itemView.setOnLongClickListener {
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
        val symbol = when (account.currency) {
            NewAccountFragment.USD_TYPE -> " $"
            NewAccountFragment.RUB_TYPE -> " ₽"
            NewAccountFragment.CHY_TYPE -> " ¥"
            NewAccountFragment.EUR_TYPE -> " €"
            else -> " "
        }
        text = "${account.total.toInt()} $symbol"

    }

    private fun TextView.formatDisplayResultPercent(account: Account) {
        text = if (TypeAccount.CURRENCY.getText(context) == account.type) {
            val percent =
                (account.total * temporaryCurrency(account) / account.totalInRub) * 100 - 100
            val resultInRub =
                account.total * temporaryCurrency(account) - account.totalInRub

            if (resultInRub > 0) setTextColor(resources.getColor(R.color.green))
            if (resultInRub < 0) setTextColor(resources.getColor(R.color.red))

            if (roundDouble(resultInRub) > 0.0 && roundDouble(percent) > 0.0)
                "+${roundDouble(resultInRub)} (+${roundDouble(percent)} %)"
            else
                "${roundDouble(resultInRub)} (${roundDouble(percent)} %)"

        } else {
            "in developing"
        }

    }

    private fun roundDouble(double: Double): Double {
        return if (double.isNaN()) 0.0 else (double * 100).roundToInt() / 100.0
    }

    private fun temporaryCurrency(account: Account): Double {
        return when (account.currency) {
            NewAccountFragment.USD_TYPE -> TEMPORARY_CURRENT_RATE_USD
            NewAccountFragment.EUR_TYPE -> TEMPORARY_CURRENT_RATE_EUR
            NewAccountFragment.CHY_TYPE -> TEMPORARY_CURRENT_RATE_CNY
            else -> {
                0.0
            }
        }
    }


    companion object {
        const val TEMPORARY_CURRENT_RATE_USD = 60.77
        const val TEMPORARY_CURRENT_RATE_EUR = 62.81
        const val TEMPORARY_CURRENT_RATE_CNY = 8.53

    }
}


