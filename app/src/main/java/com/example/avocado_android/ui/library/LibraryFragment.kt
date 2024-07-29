package com.example.avocado_android.ui.library

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentLibraryBinding
import com.example.avocado_android.domain.model.local.library.LibraryCardAdapter
import com.example.avocado_android.domain.model.local.library.OnLibraryClickListener
import com.example.avocado_android.domain.model.response.library.LibraryWordDto
import com.example.avocado_android.ui.MainViewModel
import com.example.avocado_android.ui.search.SearchFragmentDirections
import com.example.avocado_android.ui.search.SearchViewModel
import com.example.avocado_android.utils.dialog.SearchFailedDialog
import com.example.avocado_android.utils.dialog.SearchLoadingDialog
import com.example.avocado_android.utils.extensions.GridSpacingItemDecoration
import com.example.avocado_android.utils.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library),
    OnLibraryClickListener {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var libraryViewModel: LibraryViewModel
    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var searchLoadingDialog: SearchLoadingDialog
    private lateinit var searchFailedDialog: SearchFailedDialog
    private var id: Long = 0


    private lateinit var libraryAdapter: LibraryCardAdapter
    override fun setLayout() {
        getLoginId()
        initAdapter()
        setSearchBar()
        setLibrary()
        handleBackPress()
        setDialogFragment()
    }

    private fun getLoginId() {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        id = sharedPreferences.getLong("userId", 0L)
    }

    private fun setDialogFragment() {
        searchLoadingDialog = SearchLoadingDialog()
        searchFailedDialog = SearchFailedDialog()
    }

    private fun setLibrary() {
        Log.d("라이브러리",id.toString())
        if(id.toInt() != 0) {
            libraryViewModel.getLibraryData(id)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                libraryViewModel.wordCardData.collectLatest { response ->
                    libraryAdapter.submitList(response.libraryWordDtoList)
                    Glide.with(this@LibraryFragment)
                        .load(response.characterImgUrl)
                        .into(binding.libraryLogoImageIv)
                }
            }
        }
    }

    private fun initAdapter() {
        libraryViewModel = ViewModelProvider(this)[LibraryViewModel::class.java]
        libraryAdapter = LibraryCardAdapter()
        libraryAdapter.setListener(this)

        with(binding) {
            libraryWordCardRv.adapter = libraryAdapter
            libraryWordCardRv.addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2,
                    16f.fromDpToPx()
                )
            )
        }
    }

    private fun Float.fromDpToPx(): Int =
        (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun setSearchBar() {
        binding.librarySearchHereEt.setOnClickListener {
            val action = LibraryFragmentDirections.actionLibraryFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

    override fun onItemClick(item: LibraryWordDto) {
        searchWord(item.english)
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()  // 이전 화면으로 돌아가기
                }
            })
    }


    private fun searchWord(text: String) {

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
                                    val action =
                                        LibraryFragmentDirections.actionLibraryFragmentToWordListFragment(
                                            searchViewModel.queryText.first().toString()
                                        )
                                    findNavController().navigateSafe(
                                        action.actionId,
                                        action.arguments
                                    )
                                }
                            }
                        }

                        404 -> {
                            searchLoadingDialog.dismiss()
                            searchFailedDialog.show(parentFragmentManager, "SearchFailedDialog")
                        }
                    }
                }
            }
        }
    }


}