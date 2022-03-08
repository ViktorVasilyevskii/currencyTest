package com.vasilyevskii.testcurrency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.testcurrency.R
import com.vasilyevskii.testcurrency.database.AppDatabase
import com.vasilyevskii.testcurrency.database.AppDatabaseModule
import com.vasilyevskii.testcurrency.database.CurrencyData
import com.vasilyevskii.testcurrency.databinding.ItemCurrencyBinding
import com.vasilyevskii.testcurrency.model.CurrencyModel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrencyViewAdapter : RecyclerView.Adapter<CurrencyViewAdapter.ViewHolder>() {

    private lateinit var binding: ItemCurrencyBinding
    private lateinit var db: AppDatabase


    var currency: List<CurrencyModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        db = AppDatabaseModule.provideAppDatabase(context)
        binding = ItemCurrencyBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.nameCurrency.text = currency[position].nameCurrency
        binding.valueCurrency.text = currency[position].valueCurrency
        currencyFavorite(position)
    }

    private fun currencyFavorite(position: Int){
        var favoriteBoolean = false
        binding.imageStar.setOnClickListener {
            when(favoriteBoolean){
                true -> {
                    it.setBackgroundResource(R.drawable.no_favorite)
                    favoriteBoolean = false
                    db.currencyDao().delete(currency[position].nameCurrency)
                }
                false -> {
                    GlobalScope.launch {
                        kotlin.run {

                            db.currencyDao().insert(CurrencyData( currency[position].nameCurrency, currency[position].valueCurrency, true))
                        }
                    }
                    it.setBackgroundResource(R.drawable.favorite)
                    favoriteBoolean = true
                }
            }
        }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun getItemCount(): Int = currency.size
}