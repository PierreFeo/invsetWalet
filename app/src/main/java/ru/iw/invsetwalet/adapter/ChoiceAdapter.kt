package ru.iw.invsetwalet.adapter

import android.view.LayoutInflater
import android.view.View
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

internal class ChoiceAdapter(
    private val listener: AccountInteractionListener
) : ListAdapter<Account, ChoiceViewHolder>(DiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        val view =
            CardAccountFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChoiceViewHolder(listener, view)
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
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


class ChoiceViewHolder(
    private val listener: AccountInteractionListener,
    private val binding: CardAccountFragmentBinding,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(account: Account) = with(binding) {
        titleAccount.text = account.title
        descriptionAccount.formatDisplaySymbolCurrency(account)
        sumAccount.visibility =View.GONE
        percentAccount.visibility = View.GONE
        itemView.setOnClickListener {
            listener.onAccountClicked(account)
        }
    }
     fun TextView.formatDisplaySymbolCurrency(account: Account) {
        val symbol = when (account.currency) {
            NewAccountFragment.USD_TYPE -> " $"
            NewAccountFragment.RUB_TYPE -> " ₽"
            NewAccountFragment.CHY_TYPE -> " ¥"
            NewAccountFragment.EUR_TYPE -> " €"
            else -> " "
        }

        text = "${account.total.toInt()} $symbol"

    }
}


