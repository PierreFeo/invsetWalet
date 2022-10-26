package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
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
        binding.menuImageButton.setOnClickListener {
            it.visibility = View.GONE

            binding.menuImageButton.visibility = View.GONE
        }


        println("тест")
        Log.d("фффф", "ффф")
        return binding.root
    }
}


