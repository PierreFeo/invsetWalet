package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.AccountAdapter
import ru.iw.invsetwalet.databinding.HomeFragmnetBinding
import ru.iw.invsetwalet.viewModel.AccountViewModel

class HomeFragment : Fragment() {

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
        binding.menuImageButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_newAccountFragment)}

        return binding.root
    }
}


