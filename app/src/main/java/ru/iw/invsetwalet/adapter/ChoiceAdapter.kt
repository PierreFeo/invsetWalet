package ru.iw.invsetwalet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.TypeAccount

import ru.iw.invsetwalet.databinding.CardAccountFragmentBinding

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
        descriptionAccount.text = account.description
        sumAccount.visibility = View.GONE
        percentAccount.visibility = View.GONE
        root.setOnClickListener {
            listener.onAccountClicked(account)
        }
    }
}


