package com.example.avocado_android.ui.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentSearchBinding
import com.example.avocado_android.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter

    override fun setLayout() {
        setAdapter()
        searchWord()
    }

    private fun setAdapter() {
        recentWordAdapter = RecentWordAdapter()
        binding.searchRecentWordRv.adapter = recentWordAdapter

       /* viewModel.recentWordList.observe(viewLifecycleOwner) { newList ->
            recentWordAdapter.submitList(newList)
        }*/
    }

    private fun searchWord() {
        binding.searchSearchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                val query = binding.searchSearchBar.text.toString()
                viewModel.wordSearch(0, query)
                val action = SearchFragmentDirections.actionSearchFragmentToVocaListFragment()
                findNavController().navigate(action)
                true
            } else {
                false
            }
        }
    }

}