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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteViewAdapter : RecyclerView.Adapter<FavoriteViewAdapter.ViewHolder>() {

    private lateinit var binding: ItemCurrencyBinding
    private lateinit var db: AppDatabase


    var favoriteList: List<CurrencyData> = emptyList()
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
        binding.nameCurrency.text = favoriteList[position].nameCurrency
        binding.valueCurrency.text = favoriteList[position].valueCurrency
        binding.imageStar.setBackgroundResource(R.drawable.favorite)
        currencyFavorite(position)
    }

    private fun currencyFavorite(position: Int) {
        var favoriteBoolean = true
        binding.imageStar.setOnClickListener {
            when (favoriteBoolean) {
                true -> {
                    it.setBackgroundResource(R.drawable.no_favorite)
                    favoriteBoolean = false
                    db.currencyDao().delete(favoriteList[position].nameCurrency)
                }
                false -> {
                    GlobalScope.launch {
                        kotlin.run {
                            db.currencyDao().insert(
                                CurrencyData(
                                    favoriteList[position].nameCurrency,
                                    favoriteList[position].valueCurrency,
                                    true
                                )
                            )
                        }
                    }
                    it.setBackgroundResource(R.drawable.favorite)
                    favoriteBoolean = true
                }
            }
        }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = favoriteList.size
}