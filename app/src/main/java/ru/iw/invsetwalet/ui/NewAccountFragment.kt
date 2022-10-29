package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.databinding.NewAccountFragmentBinding

class NewAccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = NewAccountFragmentBinding.inflate(inflater, container, false)
        with(binding) {
            toolbarUp.setNavigationIcon(R.drawable.ic_rrow_back_24dp)
            toolbarUp.setNavigationOnClickListener { findNavController().navigateUp() }
        }




        return binding.root
    }
}