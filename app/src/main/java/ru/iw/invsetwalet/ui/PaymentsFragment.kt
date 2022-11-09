package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.data.TypeAccount
import ru.iw.invsetwalet.databinding.PaymentsFragmentBinding
import ru.iw.invsetwalet.util.ACCOUNT_ID
import ru.iw.invsetwalet.viewModel.AccountViewModel

class PaymentsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PaymentsFragmentBinding.inflate(inflater, container, false)
        val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)


        arguments?.let {
            val choiceAccount = viewModel.getAccountFromDataBase(it.getInt(ACCOUNT_ID))
            with(binding) {
                chooseAccountButton.text = choiceAccount.title
                if (choiceAccount.type != TypeAccount.CASH.toString()) exchangeRatesTextInput.visibility =
                    View.GONE
            }


        }


        binding.chooseAccountButton.setOnClickListener {
            findNavController().navigate(R.id.choiceFragment)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()


    }
}