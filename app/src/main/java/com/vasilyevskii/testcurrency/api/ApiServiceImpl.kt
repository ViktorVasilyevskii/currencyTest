package com.vasilyevskii.testcurrency.api

import com.vasilyevskii.testcurrency.model.CurrencyModel
import javax.inject.Inject

class ApiServiceImpl
@Inject
constructor(private val apiService: ApiService){
    fun getListLatestChangeRates(): List<CurrencyModel> = apiService.getListLatestChangeRates()
}