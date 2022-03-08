package com.vasilyevskii.testcurrency.api

import com.vasilyevskii.testcurrency.model.CurrencyModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.http.GET

interface ApiService {

    @GET("/latest")
    fun getListLatestChangeRates(): List<CurrencyModel>

}