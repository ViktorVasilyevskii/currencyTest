package com.vasilyevskii.testcurrency.database

import androidx.room.*

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyData WHERE favorite = 'true'")
    fun getFavorite(): List<CurrencyData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyData: CurrencyData)

    @Query("DELETE FROM CurrencyData WHERE name_currency = :name_currency")
    fun delete(name_currency: String)

}