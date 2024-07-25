package com.example.avocado_android.ui.search

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentSearchBinding
import com.example.avocado_android.ui.login.LoginViewModel
import com.example.avocado_android.ui.vocalist.PrefixAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter
    private lateinit var prefixAdapter: PrefixAdapter

    private val loginViewModel: LoginViewModel by activityViewModels()

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

    private fun getLoginId() : Long {
        var id: Long = 0L
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.memberData.collectLatest { response ->
                    Log.d("getLoginId", response.id)
                    id = response.id.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
                }
            }
        }
        return id
    }

    // 검색 창에 단어 입력하고 엔터 누르면 서버에서 값 받아와 뷰모델로 저장
    private fun searchWord() {
        binding.searchSearchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                query = binding.searchSearchBar.text.toString()

                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                // 서버 요청
                viewModel.wordSearch(getLoginId(), query)

                // 데이터가 준비된 후 화면 전환
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        val responses = viewModel.searchWordResponseDto
                            .filter { it.isSuccess == true }
                            .collect { response ->
                                if (response != null) {
                                    val action =
                                        SearchFragmentDirections.actionSearchFragmentToWordListFragment(
                                            query
                                        )
                                    findNavController().navigate(action)
                                }
                            }
                    }
                }
                true
            } else {
                false
            }
        }
    }

}