package com.vasilyevskii.testcurrency.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyData::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun currencyDao(): CurrencyDao
}