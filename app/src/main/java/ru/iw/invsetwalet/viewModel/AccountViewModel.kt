package ru.iw.invsetwalet.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.iw.invsetwalet.adapter.AccountInteractionListener
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.db.AppDb
import ru.iw.invsetwalet.repository.AccountRepository
import ru.iw.invsetwalet.repository.AccountRepositoryImpl

class AccountViewModel(application: Application) : AndroidViewModel(application),
    AccountInteractionListener {

    private val repository: AccountRepository =
        AccountRepositoryImpl(dao = AppDb.getInstance(context = application).accountDao)

    override fun onAddClicked() {
        repository.add(Account(0, "тест", "тестовое поле", "USD"))
        println("Лох")
    }


}
