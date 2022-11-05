package ru.iw.invsetwalet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.compose.ui.window.Popup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.databinding.CardAccountFragmentBinding

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
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
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
        sumAccount.text = account.currency
        percentAccount.text = account.createDate


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
}


