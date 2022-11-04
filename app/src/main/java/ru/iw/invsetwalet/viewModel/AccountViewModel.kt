package ru.iw.invsetwalet.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.iw.invsetwalet.adapter.AccountInteractionListener
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.db.AppDb
import ru.iw.invsetwalet.repository.AccountRepository
import ru.iw.invsetwalet.repository.AccountRepositoryImpl
import ru.iw.invsetwalet.util.SingleLiveEvent

class AccountViewModel(application: Application) : AndroidViewModel(application),
    AccountInteractionListener {

    private val repository: AccountRepository =
        AccountRepositoryImpl(dao = AppDb.getInstance(context = application).accountDao)

    val currentAccount = MutableLiveData<Account?>(null)
    val editAccountLiveEvent = SingleLiveEvent<Account>()
    val data = repository.getAll()

    override fun onAddClicked() {
        repository.add(
            Account(
                0, "Тестовый счет в евро", "Описание тестового счета", "CASH", "USD", "s",
                "00/00/00"
            )
        )

    }

    override fun onSaveClicked(
        title: String,
        description: String,
        type: String,
        currency: String,
        note: String,
        date: String,
    ) {
        repository.saveAccount(Account(0, title, description, type, currency, note, date))
    }

    override fun onRemoveClicked(id: Int) {
        repository.removeAccount(id)
    }

    override fun onEditClicked(account: Account) {
        editAccountLiveEvent.value = account
    }

    override fun getAccountFromDataBase(id: Int): Account {
       return repository.getAccountFromDataBase(id)
    }

}
