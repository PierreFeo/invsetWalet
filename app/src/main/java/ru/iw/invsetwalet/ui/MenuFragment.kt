package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        val binding = MenuFragmentBinding.inflate(inflater, container, false)
        binding.cardViewOpenAccount.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_newAccountFragment) }
        with(binding) {
            toolbarUp.setNavigationIcon(R.drawable.ic_rrow_back_24dp)
            toolbarUp.setNavigationOnClickListener { findNavController().navigateUp() }
        }

        return binding.root
    }
}