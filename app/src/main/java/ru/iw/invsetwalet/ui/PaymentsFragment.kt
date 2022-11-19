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
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.AccountAdapter
import ru.iw.invsetwalet.adapter.AccountViewHolder
import ru.iw.invsetwalet.adapter.ChoiceAdapter
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.databinding.PaymentsFragmentBinding
import ru.iw.invsetwalet.viewModel.AccountViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log
import kotlin.math.roundToInt

class PaymentsFragment : Fragment() {
    private lateinit var dialog: BottomSheetDialog
    private lateinit var recyclerView: RecyclerView
    private val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PaymentsFragmentBinding.inflate(inflater, container, false)

        when (arguments.let { it?.getString(HomeFragment.TYPE_TRANSACTION) }) {
            HomeFragment.ADD_PAYMENT -> {
                Log.d("arguments", "addPaymentPaint")
                //TODO CREATE FUN

            }
            HomeFragment.ADD_EXPENSE -> {
                Log.d("arguments", "addEXPENSEPaint")
        //TODO CREATE FUN
            }
            HomeFragment.TRANSFER_BETWEEN_ACCOUNTS -> {
                Log.d("arguments", "transferPaint")
                //TODO CREATE FUN
            }
            else -> {
                Log.d("arguments", "false")
                //TODO THROW EXCEPTION
            }
        }


        binding.addDebitCardView.setOnClickListener {
            showBottomSheet()


            viewModel.choiceAccountLiveEvent.observe(viewLifecycleOwner) { account ->
                binding.chooseAccountDebit.text = account.title
                binding.sumAccountDebit.formatDisplaySymbolCurrency(account)
                binding.sumAccountDebit.visibility = View.VISIBLE
                dialog.hide()

                binding.saveAccountButton.setOnClickListener {
                    with(binding) {
                        val amountTransact = paymentSumTextInput.editText?.text.toDouble()
                        val type = PAYMENT
                        val rate = exchangeRatesTextInput.editText?.text.toDouble()
                        val amountRub = amountTransact * rate
                        val date = SimpleDateFormat("dd/M/yyyy", Locale.US).format(Date())
                        viewModel.onSaveTransaction(
                            account.id,
                            amountTransact,
                            type,
                            rate,
                            amountRub,
                            date
                        )

                        findNavController().navigateUp()
                        Log.d("saveButton", account.title)
                    }

                }

            }

        }





        return binding.root
    }

    companion object {
        const val PAYMENT = "addPayment"
    }

    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.choice_account_fragment, null)
        val removeButton = dialogView.findViewById<View>(R.id.removeLine)
        val choiceAdapter = ChoiceAdapter(viewModel)

        dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyclerChoice)

        recyclerView.adapter = choiceAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            choiceAdapter.submitList(it)
        }
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

}
