package ru.iw.invsetwalet.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.iw.invsetwalet.adapter.AccountInteractionListener

class AccountViewModel(application: Application) : AndroidViewModel(application),
    AccountInteractionListener {


    override fun onClickTest() {
        TODO("Not yet implemented")
    }

}
