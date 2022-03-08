package com.vasilyevskii.testcurrency.repository

import com.vasilyevskii.testcurrency.api.ApiServiceImpl
import com.vasilyevskii.testcurrency.database.CurrencyDao
import com.vasilyevskii.testcurrency.database.CurrencyData
import com.vasilyevskii.testcurrency.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRepository
@Inject
constructor(
    private val apiServiceImpl: ApiServiceImpl,
    private val currencyDao: CurrencyDao
){

    fun getFavoriteCurrency(): Flow<List<CurrencyData>> = flow{
        emit(currencyDao.getFavorite())
    }.flowOn(Dispatchers.IO)

    fun getListLatestChangeRates(): Flow<List<CurrencyModel>> = flow {
        emit(apiServiceImpl.getListLatestChangeRates())
    }.flowOn(Dispatchers.IO)


}