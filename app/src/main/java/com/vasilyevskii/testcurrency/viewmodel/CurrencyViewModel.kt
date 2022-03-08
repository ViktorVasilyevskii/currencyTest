package com.vasilyevskii.testcurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyevskii.testcurrency.database.CurrencyData
import com.vasilyevskii.testcurrency.model.CurrencyModel
import com.vasilyevskii.testcurrency.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel
@Inject
constructor(
    private val currencyRepository: CurrencyRepository
): ViewModel() {

    private val currencyFavorite : MutableStateFlow<List<CurrencyData>> = MutableStateFlow(
        emptyList())
    val _currencyFavorite : StateFlow<List<CurrencyData>> = currencyFavorite
    fun getCurrencyFavorite() = viewModelScope.launch {
        currencyRepository.getFavoriteCurrency().collect {
            currencyFavorite.value = it
        }
    }

    private val currencyAll : MutableStateFlow<List<CurrencyModel>> = MutableStateFlow(emptyList())
    val _currencyAll : StateFlow<List<CurrencyModel>> = currencyAll
    fun getCurrencyAll() = viewModelScope.launch {
        currencyRepository.getListLatestChangeRates().collect {
            currencyAll.value = it
        }
    }
}