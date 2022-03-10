package com.example.datastore.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.datastore.domain.repo.GeneralRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val generalRepository: GeneralRepository
) : ViewModel(){

    fun storeName(name : String) {
        viewModelScope.launch {
            generalRepository.storeName(name)
        }
    }

    val name = generalRepository.getName().asLiveData()

    class Factory(
        private val generalRepository: GeneralRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(generalRepository) as T
        }
    }
}