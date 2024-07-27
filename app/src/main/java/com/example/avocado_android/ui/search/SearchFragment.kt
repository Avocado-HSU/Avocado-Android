package com.example.avocado_android.ui.search

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
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
import com.example.avocado_android.utils.dialog.SearchLoadingDialog
import com.example.avocado_android.utils.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private val searchViewModel : SearchViewModel by activityViewModels()
    private lateinit var recentWordAdapter: RecentWordAdapter
    private lateinit var prefixAdapter: PrefixAdapter

    private lateinit var searchLoadingDialog: SearchLoadingDialog
    private lateinit var searchFailedDialog: SearchFailedDialog
    private var id : Long = 0

    override fun setLayout() {
        getLoginId()
        handleBackPress()
        setDialogFragment()
        setAdapter()
        observeViewModel()
        editSearchWord()
    }

    private fun setDialogFragment() {
        searchLoadingDialog = SearchLoadingDialog()
        searchFailedDialog = SearchFailedDialog()
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

        searchViewModel.resetStatus() // HttpStatus reset

        lifecycleScope.launch {
            searchViewModel.wordSearch(id, text)
        }

        viewLifecycleOwner.lifecycleScope.launch {
           searchLoadingDialog.show(parentFragmentManager, "SearchLoadingDialog")
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.httpStatusCode.collectLatest { statusCode ->
                    when (statusCode) {
                        200 -> {
                            searchViewModel.searchWordResponseDto.collectLatest { response ->
                                if (response.isSuccess == true) {
                                    searchLoadingDialog.dismiss()
                                    val action = SearchFragmentDirections.actionSearchFragmentToWordListFragment(searchViewModel.queryText.first().toString())
                                    findNavController().navigateSafe(action.actionId, action.arguments)
                                }
                            }
                        }

                        404 -> {
                            searchLoadingDialog.dismiss()
                            searchFailedDialog.onDismissListener = { binding.searchSearchBarEt.text = null }
                            searchFailedDialog.show(parentFragmentManager, "SearchFailedDialog")
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
                val query = binding.searchSearchBarEt.text.toString()
                Log.d("쿼리", "$query")
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