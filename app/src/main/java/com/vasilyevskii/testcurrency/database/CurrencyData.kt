package com.vasilyevskii.testcurrency.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyData(
    @ColumnInfo(name = "name_currency") var nameCurrency: String,
    @ColumnInfo(name = "value_currency") var valueCurrency: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}