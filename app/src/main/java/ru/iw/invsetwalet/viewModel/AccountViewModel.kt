package ru.iw.invsetwalet.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.iw.invsetwalet.adapter.AccountInteractionListener
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.Transactions
import ru.iw.invsetwalet.repository.RoomRepository
import ru.iw.invsetwalet.repository.RoomRepositoryImpl
import ru.iw.invsetwalet.util.SingleLiveEvent

class AccountViewModel(application: Application) : AndroidViewModel(application),
    AccountInteractionListener {

    val newAccount = Account(
        NEW_ID,
        "",
        "",
        "",
        "",
        "",
        0.00,
        0.00,
        "",
        false
    )

    val newTransaction = Transactions(
        NEW_ID,
        0,
        0.0,
        "",
        0.00,
        0.00,
        ""
    )

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
//        repository.add(
//            Account(
//                0, "Тестовый счет в евро", "Описание тестового счета", "CASH", "USD", "s",
//                System.currentTimeMillis().toDouble()
//            )
//        )

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
        ) ?: newAccount.copy(
            title = title,
            description = description,
            type = type,
            currency = currency,
            note = note,
            createDate = date
        )
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

    override fun onSaveTransaction(
        accountId: Int,
        amountTransact: Double,
        typeTransact: String,
        ratesBuy: Double?,
        result:Double,
        createDate: String
    ) {
        repository.saveTransaction(
            newTransaction.copy(
                accountId = accountId,
                amountTransact = amountTransact,
                typeTransact = typeTransact,
                ratesBuy = ratesBuy,
                resultTransaction = result,
                createDate = createDate
            )
        )
    }

    companion object {
        const val NEW_ID = 0

    }

    fun resetCurrentAccount() {
        currentAccount.value = null
    }


}
