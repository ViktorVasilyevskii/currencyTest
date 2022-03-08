package com.vasilyevskii.testcurrency.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.vasilyevskii.testcurrency.R
import com.vasilyevskii.testcurrency.databinding.FragmentSortBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSort: Fragment(R.layout.fragment_sort) {

    private lateinit var binding: FragmentSortBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding = FragmentSortBinding.bind(itemView)

        binding.buttonAscendingAlphabet.setOnClickListener {
            setFragmentResult("SortResult", bundleOf("Alphabet" to "Ascending"))
            startFragmentMain()
        }

        binding.buttonDescendingAlphabet.setOnClickListener {
            setFragmentResult("SortResult", bundleOf("Alphabet" to "Descending"))
            startFragmentMain()
        }

        binding.buttonAscendingValue.setOnClickListener {
            setFragmentResult("SortResult", bundleOf("Value" to "Ascending"))
            startFragmentMain()
        }

        binding.buttonDescendingValue.setOnClickListener {
            setFragmentResult("SortResult", bundleOf("Value" to "Descending"))
            startFragmentMain()
        }

    }

    private fun startFragmentMain(){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container_view, FragmentMain())?.commit()
    }
}