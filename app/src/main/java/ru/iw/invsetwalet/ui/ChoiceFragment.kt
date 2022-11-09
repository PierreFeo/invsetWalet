package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.AccountAdapter
import ru.iw.invsetwalet.adapter.ChoiceAdapter
import ru.iw.invsetwalet.data.Account
import ru.iw.invsetwalet.databinding.ChoiceAccountFragmentBinding
import ru.iw.invsetwalet.util.ACCOUNT_ID
import ru.iw.invsetwalet.util.bundle
import ru.iw.invsetwalet.viewModel.AccountViewModel


class ChoiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: AccountViewModel by viewModels(ownerProducer = ::requireParentFragment)
        val binding = ChoiceAccountFragmentBinding.inflate(inflater, container, false)

        val choiceAdapter = ChoiceAdapter(viewModel)

        binding.recyclerChoice.adapter = choiceAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            choiceAdapter.submitList(it)
        }




        viewModel.data.observe(viewLifecycleOwner) {
            choiceAdapter.submitList(it)
        }

        viewModel.choiceAccountLiveEvent.observe(viewLifecycleOwner) {
            bundle.putInt(ACCOUNT_ID, it.id)
            findNavController().navigate(R.id.paymentsFragment, bundle)

        }

        return binding.root
    }
}

