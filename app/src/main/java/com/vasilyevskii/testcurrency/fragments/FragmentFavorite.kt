package com.vasilyevskii.testcurrency.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilyevskii.testcurrency.R
import com.vasilyevskii.testcurrency.adapter.FavoriteViewAdapter
import com.vasilyevskii.testcurrency.databinding.FragmentFavoriteBinding
import com.vasilyevskii.testcurrency.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentFavorite: Fragment(R.layout.fragment_favorite) {

    private val recyclerFavoriteViewAdapter = FavoriteViewAdapter()
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        initRecycleView(view.context)
        loadData()
    }

    private fun loadData(){
        lifecycleScope.launchWhenStarted {
            viewModel.getCurrencyFavorite()
            viewModel._currencyFavorite.collect {
               recyclerFavoriteViewAdapter.favoriteList = it
            }
        }
    }

    private fun initRecycleView(context: Context){
        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerFavoriteViewAdapter
        }
    }
}