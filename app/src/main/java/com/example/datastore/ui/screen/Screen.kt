package com.example.datastore.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.R
import com.example.datastore.databinding.FragmentScreenBinding
import com.example.datastore.domain.repo.GeneralRepository
import com.example.datastore.ui.viewmodels.HomeViewModel

class Screen : Fragment(R.layout.fragment_screen) {

    private lateinit var binding : FragmentScreenBinding

    private val homeViewModel : HomeViewModel by lazy {
        ViewModelProvider(this,
            HomeViewModel.Factory(GeneralRepository.get(requireContext())))[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentScreenBinding.bind(view)
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener { saveNameInPrefs() }

        homeViewModel.name.observe(viewLifecycleOwner) {
            if (it != null) binding.name.text = it
        }
    }


    private fun saveNameInPrefs() {
        val name = binding.nameBox.text?.toString() ?: getString(R.string.empty_name_msg)
        homeViewModel.storeName(name)
    }
}