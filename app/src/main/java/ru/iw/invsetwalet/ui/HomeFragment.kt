package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.AccountAdapter
import ru.iw.invsetwalet.databinding.HomeFragmnetBinding
import ru.iw.invsetwalet.util.ACCOUNT_ID
import ru.iw.invsetwalet.util.bundle
import ru.iw.invsetwalet.viewModel.AccountViewModel


class HomeFragment : Fragment() {

    private lateinit var dialog: BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val binding = HomeFragmnetBinding.inflate(inflater, container, false)
        val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)

        binding.refreshImageView.setOnClickListener { viewModel.onAddClicked() }


        val adapter = AccountAdapter(viewModel)

        binding.recyclerViewAccount.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.menuImageButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_menuFragment) }

        requireActivity().actionBar?.hide()


        binding.actionImageView.setOnClickListener {
            showSheetAction()
        }



        viewModel.editAccountLiveEvent.observe(viewLifecycleOwner) {
            bundle.putInt(ACCOUNT_ID, it.id)
            findNavController().navigate(R.id.newAccountFragment, bundle)
        }



        binding.sortImageButton.setOnClickListener {
            findNavController().navigate(R.id.paymentsFragment)
        }


        return binding.root
    }

    private fun showSheetAction() {
        val dialogView = layoutInflater.inflate(R.layout.payments_action, null)
        val addPayment = dialogView.findViewById<View>(R.id.addPayment)
        val addExpense = dialogView.findViewById<View>(R.id.addExpense)
        val transferBetweenAccounts = dialogView.findViewById<View>(R.id.transferBetween)

        dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        dialog.show()

        addPayment.setOnClickListener {
            dialog.hide()
            bundle.putString(TYPE_TRANSACTION, ADD_PAYMENT)
            findNavController().navigate(R.id.paymentsFragment, bundle)
        }

        addExpense.setOnClickListener {
            dialog.hide()
            bundle.putString(TYPE_TRANSACTION, ADD_EXPENSE)
            findNavController().navigate(R.id.paymentsFragment, bundle)
        }
        transferBetweenAccounts.setOnClickListener {
            dialog.hide()
            bundle.putString(TYPE_TRANSACTION, TRANSFER_BETWEEN_ACCOUNTS)
            findNavController().navigate(R.id.paymentsFragment, bundle)
        }


    }

    companion object {
        const val TYPE_TRANSACTION = "typeTransaction"
        const val ADD_PAYMENT = "addPayment"
        const val ADD_EXPENSE = "addExpense"
        const val TRANSFER_BETWEEN_ACCOUNTS = "transferBetweenAccounts"
    }
}





