package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.adapter.AccountAdapter
import ru.iw.invsetwalet.databinding.HomeFragmnetBinding
import ru.iw.invsetwalet.util.ACCOUNT_ID
import ru.iw.invsetwalet.util.bundle
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
        binding.menuImageButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_menuFragment) }

        requireActivity().actionBar?.hide()


        fun toggle(show: Boolean) {
            val animation: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.animator.alpha.toInt())

            val transition: Transition = Slide(Gravity.BOTTOM)
            transition.duration = 300
            transition.addTarget(R.id.bottomMenuLinearLayout)
            TransitionManager.beginDelayedTransition(binding.relativeLayout, transition)

            if (show) {
                with(binding) {
                    grayBackgroundLinearLayout.startAnimation(animation)
                    bottomMenuLinearLayout.visibility = View.VISIBLE
                    grayBackgroundLinearLayout.visibility = View.VISIBLE
                }

            } else {
                binding.bottomMenuLinearLayout.visibility = View.GONE
                binding.grayBackgroundLinearLayout.visibility = View.GONE
            }
        }


        binding.actionImageView.setOnClickListener {
            toggle(binding.actionImageView.isChecked)

        }


        binding.grayBackgroundLinearLayout.setOnClickListener {
            binding.actionImageView.isChecked = false
            toggle(binding.actionImageView.isChecked)
            //TODO create group
        }

        viewModel.editAccountLiveEvent.observe(viewLifecycleOwner) {
            bundle.putInt(ACCOUNT_ID, it.id)
            findNavController().navigate(R.id.newAccountFragment, bundle)

        }

        return binding.root
    }


}


