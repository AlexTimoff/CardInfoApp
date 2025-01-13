package com.example.alphatesttasktimofeev.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alphatesttasktimofeev.domain.AllRequestsUseCase
import com.example.alphatesttasktimofeev.domain.GetCardInfoUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val allRequestsUseCase: AllRequestsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getCardInfoUseCase, allRequestsUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}