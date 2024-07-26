package com.example.avocado_android.ui.search

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
    private val searchViewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter
    private lateinit var prefixAdapter: PrefixAdapter

    private var id : Long = 0
    private lateinit var query: String

    override fun setLayout() {
        getLoginId()
        handleBackPress()
        setAdapter()
        observeViewModel()
        editSearchWord()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.getRecentSearch(id)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.recentWordList.collectLatest { recentWordList->
                    recentWordAdapter.submitList(recentWordList.recentSearchWords)
                }
            }
        }
    }

    private fun setAdapter() {
        recentWordAdapter = RecentWordAdapter { item ->
            searchWord(item)
        }
        binding.searchRecentWordRv.adapter = recentWordAdapter
    }

    private fun getLoginId() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        id = sharedPreferences.getLong("userId", 0L)
    }

    // searchViewModel 통해 실제 서버 검색 API 실행
    private fun searchWord(text : String) {
        lifecycleScope.launch {
            searchViewModel.wordSearch(id, text)
        }

        // 데이터가 준비된 후 화면 전환
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.searchWordResponseDto.collect{ response ->
                        if (response.isSuccess == true) {
                            val action =
                                SearchFragmentDirections.actionSearchFragmentToWordListFragment(
                                    text
                                )
                            findNavController().navigate(action)
                        }
                    }
                }

                // http status -> 404이면 다이얼로그 띄움
                launch {
                    searchViewModel.httpStatusCode.collect { statusCode ->
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
    }

    // 검색 창에 단어 입력 후 엔터 누르면 검색 하는 함수 호출
    private fun editSearchWord() {
        binding.searchSearchBarEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                query = binding.searchSearchBarEt.text.toString()

                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                searchWord(query)

                true
            } else {
                false
            }
        }
    }

    // 뒤로가기 버튼 처리
    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()  // 이전 화면으로 돌아가기
            }
        })
    }
}