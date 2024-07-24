package com.example.avocado_android.ui.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentSearchBinding
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.ui.home.HomeFragmentDirections
import com.example.avocado_android.ui.vocalist.AffixAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter
    private lateinit var affixAdapter: AffixAdapter

    private lateinit var query: String

    override fun setLayout() {
        setAdapter()
        searchWord()
    }

    private fun setAdapter() {
        recentWordAdapter = RecentWordAdapter()
        binding.searchRecentWordRv.adapter = recentWordAdapter

        //affixAdapter = AffixAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchWordResponseDto.collect() { data ->
                //affixAdapter.submitList(data)
            }
        }


    }

    // 검색 창에 단어 입력하고 엔터 누르면 서버에서 값 받아와 뷰모델로 저장
    private fun searchWord() {
        binding.searchSearchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                query = binding.searchSearchBar.text.toString()

                viewModel.wordSearch(14, query)
                val action = SearchFragmentDirections.actionSearchFragmentToWordListFragment(query)
                findNavController().navigate(action)

                true
            } else {
                false
            }
        }
    }

}