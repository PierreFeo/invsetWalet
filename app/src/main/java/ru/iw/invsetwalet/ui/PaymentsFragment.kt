package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.ChoiceAdapter
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.data.TypeAccount
import ru.iw.invsetwalet.databinding.PaymentsFragmentBinding
import ru.iw.invsetwalet.viewModel.AccountViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.E

class PaymentsFragment : Fragment() {
    private lateinit var dialog: BottomSheetDialog
    private lateinit var recyclerView: RecyclerView
    private val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)
    lateinit var binding: PaymentsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PaymentsFragmentBinding.inflate(inflater, container, false)

        when (arguments.let { it?.getString(HomeFragment.TYPE_TRANSACTION) }) {
            HomeFragment.ADD_PAYMENT -> {
                //TODO Create fun

                Log.d("arguments", "click addPayment")
                binding.allDebitViews.visibility = View.GONE

                binding.addDepositCardView.setOnClickListener {
                    showBottomSheet()

                    viewModel.choiceAccountLiveEvent.observe(viewLifecycleOwner) { account ->
                        switchViewsPayments(account)

                        binding.chooseAccountDeposit.text = account.title
                        binding.sumAccountDeposit.formatDisplaySymbolCurrency(account)
                        binding.sumAccountDeposit.visibility = View.VISIBLE
                        dialog.hide()

                        binding.saveAccountButton.setOnClickListener {
                            if (account.type == TypeAccount.CURRENCY.getText(requireContext()))
                                saveCurrencyTypeAccount(account)
                            else saveOtherTypeAccount(account)
                        }

                    }

                }

            }
            HomeFragment.ADD_EXPENSE -> {
                Log.d("arguments", "click addExpense")
                binding.allDepositViews.visibility = View.GONE


                binding.addDebitCardView.setOnClickListener {
                    showBottomSheet()
                    viewModel.choiceAccountLiveEvent.observe(viewLifecycleOwner) { account ->
                        switchViewsPayments(account)


                            //TODO im stop here

                        binding.chooseAccountDebit.text = account.title
                        binding.sumAccountDebit.formatDisplaySymbolCurrency(account)
                        binding.sumAccountDebit.visibility = View.VISIBLE
                        dialog.hide()

//                        binding.saveAccountButton.setOnClickListener {
//                            if (account.type == TypeAccount.CURRENCY.getText(requireContext()))
//                                saveCurrencyTypeAccount(account)
//                            else saveOtherTypeAccount(account)
//                        }

                    }


                }
            }
            HomeFragment.TRANSFER_BETWEEN_ACCOUNTS -> {
                Log.d("arguments", "click addTransfer")
                //TODO CREATE FUN
            }
            else -> {
                Log.d("arguments", "false")
                //TODO THROW EXCEPTION
            }
        }










        return binding.root
    }



    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.choice_account_fragment, null)
        val removeButton = dialogView.findViewById<View>(R.id.removeLine)
        val choiceAdapter = ChoiceAdapter(viewModel)

        dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyclerChoice)

        recyclerView.adapter = choiceAdapter

        viewModel.data.value.let { choiceAdapter.submitList(it) }

        dialog.show()

        removeButton.setOnClickListener {
            dialog.hide()
        }
    }

    private fun Editable?.toDouble() = (toString().toDouble())


    private fun TextView.formatDisplaySymbolCurrency(account: Account) {
        val symbol = when (account.currency) {
            NewAccountFragment.USD_TYPE -> " $"
            NewAccountFragment.RUB_TYPE -> " ₽"
            NewAccountFragment.CHY_TYPE -> " ¥"
            NewAccountFragment.EUR_TYPE -> " €"
            else -> " "
        }
        text = "${account.total.toInt()}$symbol"

    }

    private fun switchViewsPayments(account: Account) {
        with(binding) {
            if (account.type == TypeAccount.CURRENCY.getText(requireContext())) {
                paymentSumTextInput.visibility = View.VISIBLE
                exchangeRatesTextInput.visibility = View.VISIBLE
                profitCheckBox.visibility = View.GONE
            } else {
                paymentSumTextInput.visibility = View.VISIBLE
                exchangeRatesTextInput.visibility = View.GONE
                profitCheckBox.visibility = View.VISIBLE
            }
        }
    }

    private fun saveCurrencyTypeAccount(account: Account) {
        with(binding) {
            val amountTransact = paymentSumTextInput.editText?.text.toDouble()
            val type = PAYMENT
            val rate = exchangeRatesTextInput.editText?.text.toDouble()
            val result = amountTransact * rate
            val date = SimpleDateFormat("dd/M/yyyy", Locale.US).format(Date())
            viewModel.onSaveTransaction(
                account.id,
                amountTransact,
                type,
                rate,
                result,
                date
            )

            findNavController().navigateUp()
            Log.d("saveButton", account.title)
        }
    }

    private fun saveOtherTypeAccount(account: Account) {
        with(binding) {
            val amountTransact =
                if (profitCheckBox.isChecked) EMPTY_TRANSITION else paymentSumTextInput.editText?.text.toDouble()
            val type = PAYMENT
            val result =
                if (amountTransact == EMPTY_TRANSITION) paymentSumTextInput.editText?.text.toDouble() else EMPTY_TRANSITION
            val date = SimpleDateFormat("dd/M/yyyy", Locale.US).format(Date())
            viewModel.onSaveTransaction(
                account.id,
                amountTransact,
                type,
                null,
                result,
                date
            )

            findNavController().navigateUp()
            Log.d("saveButton", binding.profitCheckBox.isChecked.toString())
        }
    }
    companion object {
        const val PAYMENT = "addPayment"
        const val EMPTY_TRANSITION = 0.0
    }

}
