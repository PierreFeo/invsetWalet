package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.data.TypeAccount
import ru.iw.invsetwalet.databinding.NewAccountFragmentBinding
import ru.iw.invsetwalet.util.ACCOUNT_ID
import ru.iw.invsetwalet.viewModel.AccountViewModel
import java.text.SimpleDateFormat
import java.util.*

class NewAccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)
        val binding = NewAccountFragmentBinding.inflate(inflater, container, false)


        arguments?.let {
            val editedAccount = viewModel.getAccountFromDataBase(
                it.getInt(
                    ACCOUNT_ID
                )
            )
            with(binding) {
                accountTitleEditText.setText(editedAccount.title)
                accountDescriptionEditText.setText(editedAccount.description)
                typeAccountEditTextView.editText?.setText(editedAccount.type)
                currencyAccountEditTextView.editText?.setText(editedAccount.currency)
                noteEditText.setText(editedAccount.note)
            }
        }


        val currencyArray = listOf(RUB_TYPE, USD_TYPE, EUR_TYPE, CHY_TYPE)
        val adapterCurrency = ArrayAdapter(requireContext(), R.layout.list_item, currencyArray)
        (binding.currencyAccountEditTextView.editText as? AutoCompleteTextView)?.setAdapter(
            adapterCurrency
        )

        val typeAccountArray =
            TypeAccount.values().map { it.getText(requireContext()) }.toTypedArray()
        val adapterTypeAccount =
            ArrayAdapter(requireContext(), R.layout.list_item, typeAccountArray)
        (binding.typeAccountEditTextView.editText as? AutoCompleteTextView)?.setAdapter(
            adapterTypeAccount
        )

        binding.saveAccountButton.setOnClickListener {

            with(binding) {
                val title = accountTitleEditText.text.toString()
                val description = accountDescriptionEditText.text.toString()
                val typeAccount = typeAccountEditTextView.editText?.text.toString()
                val currency = currencyAccountEditTextView.editText?.text.toString()
                val note = noteEditText.text.toString()
                val sdf = SimpleDateFormat("dd/M/yyyy", Locale.US)
                val createDate = sdf.format(Date())

                viewModel.onSaveClicked(title, description, typeAccount, currency, note, createDate)
                findNavController().navigate(R.id.homeFragment)
            }

        }

        return binding.root

    }

    companion object {
        const val RUB_TYPE = "RUB"
        const val USD_TYPE = "USD"
        const val EUR_TYPE = "EUR"
        const val CHY_TYPE = "CNY"
    }


}


