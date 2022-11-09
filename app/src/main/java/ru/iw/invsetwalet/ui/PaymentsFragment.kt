package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.ChoiceAdapter
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



        val choiceAdapter = ChoiceAdapter(viewModel)
        binding.recyclerChoice.adapter = choiceAdapter
        viewModel.data.observe(viewLifecycleOwner) {
            choiceAdapter.submitList(it)
        }


        binding.chooseAccountButton.setOnClickListener {
            with(binding) {
                choiceEditText.visibility = View.GONE
                constraintWithRecycler.visibility = View.VISIBLE
            }

        }

        viewModel.choiceAccountLiveEvent.observe(viewLifecycleOwner) { account ->
            with(binding) {
                chooseAccountButton.text = account.title
                choiceEditText.visibility = View.VISIBLE
                constraintWithRecycler.visibility = View.GONE
            }


            binding.saveAccountButton.setOnClickListener {
                //TODO insert room dataBase
                Log.d("saveButton", account.title)
            }
        }
        return binding.root
    }

}