package com.example.avocado_android.ui.library

import android.content.res.Resources
import android.net.Uri
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
import com.example.avocado_android.ui.MainViewModel
import com.example.avocado_android.utils.extensions.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library) {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var libraryViewModel : LibraryViewModel

    private lateinit var libraryAdapter: LibraryCardAdapter
    override fun setLayout() {
        initAdapter()
        setSearchBar()
        setLibrary()
    }
    private fun setLibrary(){
        libraryViewModel.getLibraryData(9)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                libraryViewModel.wordCardData.collectLatest {response ->
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

        with(binding) {
            libraryWordCardRv.adapter = libraryAdapter
            libraryWordCardRv.addItemDecoration(GridSpacingItemDecoration(spanCount = 2, 16f.fromDpToPx()))
        }
    }
    private fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun setSearchBar() {
        binding.librarySearchHereEt.setOnClickListener {
            val action = LibraryFragmentDirections.actionLibraryFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}