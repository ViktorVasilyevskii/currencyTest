package com.vasilyevskii.testcurrency.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilyevskii.testcurrency.R
import com.vasilyevskii.testcurrency.adapter.CurrencyViewAdapter
import com.vasilyevskii.testcurrency.databinding.FragmentMainBinding
import com.vasilyevskii.testcurrency.viewmodel.CurrencyViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentMain : Fragment(R.layout.fragment_main) {

    private val recyclerViewAdapter = CurrencyViewAdapter()
    private lateinit var binding: FragmentMainBinding
    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var sortResultCurrency: String
    private lateinit var sortResultValue: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        initRecycleView(view.context)
        loadData()
    }

    private fun loadData(){
        setFragmentResultListener("SortResult") { _, bundle ->
            sortResultCurrency = bundle.getString("Alphabet").toString()
            sortResultValue = bundle.getString("Value").toString()
            lifecycleScope.launchWhenStarted {
                viewModel.getCurrencyAll()
                viewModel._currencyAll.collect { it ->
                    when (sortResultCurrency) {
                        "Ascending" -> {
                            it.sortedBy { it.nameCurrency }
                            recyclerViewAdapter.currency = it
                        }
                        "Descending" -> {
                            it.sortedByDescending { it.nameCurrency }
                            recyclerViewAdapter.currency = it
                        }
                    }
                    when (sortResultValue) {
                        "Ascending" -> {
                            it.sortedBy { it.valueCurrency }
                            recyclerViewAdapter.currency = it
                        }
                        "Descending" -> {
                            it.sortedByDescending { it.valueCurrency }
                            recyclerViewAdapter.currency = it
                        }
                    }

                }
            }
        }
    }


    private fun initRecycleView(context: Context){
        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }
}