package com.example.avocado_android.ui.search

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
import com.example.avocado_android.utils.dialog.SearchFailedDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private val viewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter
    private lateinit var prefixAdapter: PrefixAdapter

    private var id : Long = 0
    private lateinit var query: String

    override fun setLayout() {
        getLoginId()
        setAdapter()
        observeViewModel()
        searchWord()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getRecentSearch(id)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recentWordList.collectLatest { recentWordList->
                    val limitedList = recentWordList.recentSearchWords.take(5)
                    recentWordAdapter.submitList(limitedList)
                }
            }
        }
    }


    private fun setAdapter() {
        recentWordAdapter = RecentWordAdapter()
        binding.searchRecentWordRv.adapter = recentWordAdapter
    }

    private fun getLoginId() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        id = sharedPreferences.getLong("userId", 0L)
    }

    // 검색 창에 단어 입력하고 엔터 누르면 서버에서 값 받아와 뷰모델로 저장
    private fun searchWord() {
        binding.searchSearchBarEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                query = binding.searchSearchBarEt.text.toString()

                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                // 서버 요청
                lifecycleScope.launch {
                    viewModel.wordSearch(id, query)
                }

                // 데이터가 준비된 후 화면 전환
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        launch {
                            viewModel.searchWordResponseDto.collect{ response ->
                                if (response.isSuccess == true) {
                                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                                    val action =
                                        SearchFragmentDirections.actionSearchFragmentToWordListFragment(
                                            query
                                        )
                                    findNavController().navigate(action)
                                }
                            }
                        }

                        // http status -> 404이면 다이얼로그 띄움
                        launch {
                            viewModel.httpStatusCode.collect { statusCode ->
                                when(statusCode) {
                                    404 -> {
                                        val dialog = SearchFailedDialog()
                                        dialog.onDismissListener = {
                                            binding.searchSearchBarEt.text = null
                                        }
                                        dialog.show(parentFragmentManager, "SearchFailedDialog")
                                    }
                                }
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