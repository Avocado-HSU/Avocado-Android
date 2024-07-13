package com.example.avocado_android.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter

    override fun setLayout() {
        setAdapter()
    }

    private fun setAdapter() {
        recentWordAdapter = RecentWordAdapter()
        binding.searchRecentWordRv.adapter = recentWordAdapter

        viewModel.recentWordList.observe(viewLifecycleOwner) { newList ->
            recentWordAdapter.submitList(newList)
        }


    }

}