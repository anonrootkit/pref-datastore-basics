package com.example.datastore.ui.screen

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.R
import com.example.datastore.databinding.FragmentScreenBinding
import com.example.datastore.domain.repo.GeneralRepository
import com.example.datastore.ui.viewmodels.HomeViewModel
import com.example.datastore.utils.PreferenceConstants.NAME_KEY
import com.example.datastore.utils.PreferenceConstants.PREFERENCE_NAME

class Screen : Fragment(R.layout.fragment_screen), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding : FragmentScreenBinding

    private val homeViewModel : HomeViewModel by lazy {
        ViewModelProvider(this,
            HomeViewModel.Factory(GeneralRepository.get(requireContext())))[HomeViewModel::class.java]
    }

    private val sharedPreferences : SharedPreferences by lazy {
        requireContext().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentScreenBinding.bind(view)
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener { saveNameInPrefs() }

        getNameFromPrefs()
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun getNameFromPrefs() {
        val name = homeViewModel.getName() ?: return
        binding.name.text = name
    }

    private fun saveNameInPrefs() {
        val name = binding.nameBox.text?.toString() ?: getString(R.string.empty_name_msg)
        homeViewModel.storeName(name)
    }

    override fun onSharedPreferenceChanged(prefs: SharedPreferences, key: String) {
        if (key == NAME_KEY) binding.name.text = prefs.getString(NAME_KEY, null)
    }
}