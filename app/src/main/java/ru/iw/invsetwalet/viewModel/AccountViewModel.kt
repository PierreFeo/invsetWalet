package ru.iw.invsetwalet.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.iw.invsetwalet.adapter.AccountInteractionListener
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.repository.RoomRepository
import ru.iw.invsetwalet.repository.RoomRepositoryImpl
import ru.iw.invsetwalet.util.SingleLiveEvent

class AccountViewModel(application: Application) : AndroidViewModel(application),
    AccountInteractionListener {

    //    private val repository: RoomRepository =
//        RoomRepositoryImpl(dao = AppDb.getInstance(context = application).accountDao)
//
    private val repository: RoomRepository = RoomRepositoryImpl(application)


    val currentAccount = MutableLiveData<Account?>(null)
    val data = repository.getAll()


    val editAccountLiveEvent = SingleLiveEvent<Account>()
    val navigateAddPaymentSingleLiveEvent = SingleLiveEvent<Boolean>()
    val choiceAccountLiveEvent = SingleLiveEvent<Account>()

    override fun onAddClicked() {
        repository.add(
            Account(
                0, "Тестовый счет в евро", "Описание тестового счета", "CASH", "USD", "s",
                System.currentTimeMillis().toString()
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
        val account = currentAccount.value?.copy(
            title = title,
            description = description,
            type = type,
            currency = currency,
            note = note
        ) ?: Account(NEW_ACCOUNT_ID, title, description, type, currency, note, date)
        repository.saveAccount(account)
        resetCurrentAccount()
    }

    override fun onRemoveClicked(id: Int) {
        repository.removeAccount(id)
    }

    override fun onEditClicked(account: Account) {
        editAccountLiveEvent.value = account
        currentAccount.value = account
    }

    override fun getAccountFromDataBase(id: Int): Account {
        return repository.getAccountFromDataBase(id)
    }

    override fun onAddPaymentClicked(boolean: Boolean) {
        navigateAddPaymentSingleLiveEvent.value = boolean
    }

    override fun onAccountClicked(account: Account) {
        choiceAccountLiveEvent.value = account
    }

    companion object {
        const val NEW_ACCOUNT_ID = 0
    }

    fun resetCurrentAccount() {
        currentAccount.value = null
    }

}
