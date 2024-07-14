package com.example.avocado_android.ui.library

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentLibraryBinding
import com.example.avocado_android.domain.model.library.LibraryCardAdapter
import com.example.avocado_android.ui.MainViewModel
import com.example.avocado_android.ui.home.BestChoiceAdapter
import com.example.avocado_android.ui.home.CheckDaysAdapter
import com.example.avocado_android.ui.home.HomeFragmentDirections
import com.example.avocado_android.ui.home.RecommendedWordAdapter
import com.example.avocado_android.utils.extensions.GridSpacingItemDecoration
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration

class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library) {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var libraryAdapter: LibraryCardAdapter
    override fun setLayout() {
        initAdapter()
    }

    private fun initAdapter() {
        libraryAdapter = LibraryCardAdapter()

        with(binding) {
            libraryWordCardRv.adapter = libraryAdapter
            libraryWordCardRv.addItemDecoration(GridSpacingItemDecoration(spanCount = 2, 16f.fromDpToPx()))
        }

        viewModel.libraryWordCardItem.observe(viewLifecycleOwner) { newList ->
            libraryAdapter.submitList(newList)
        }
    }
    private fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}