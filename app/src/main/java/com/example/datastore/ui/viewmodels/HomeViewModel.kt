package com.example.datastore.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datastore.domain.repo.GeneralRepository

class HomeViewModel(
    private val generalRepository: GeneralRepository
) : ViewModel(){

    fun storeName(name : String) = generalRepository.storeName(name)

    fun getName() : String? = generalRepository.getName()

    class Factory(
        private val generalRepository: GeneralRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(generalRepository) as T
        }
    }
}