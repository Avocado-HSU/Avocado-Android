package com.example.avocado_android.ui.vocalist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentVocaListBinding
import com.example.avocado_android.ui.library.LibraryFragmentDirections
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration

class VocaListFragment : BaseFragment<FragmentVocaListBinding>(R.layout.fragment_voca_list) {

    private val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(0)

    private val vocaListViewModel by activityViewModels<VocaListViewModel>()
    private lateinit var affixAdapter: AffixAdapter
    private lateinit var sameWordAdapter: SameWordAdapter
    private lateinit var suffixAdapter: SuffixAdapter

    override fun setLayout() {
        setAdapter()
        observeViewModel()
        setSearchBar()
        setButton()
    }

    private fun setAdapter() {
        affixAdapter = AffixAdapter()
        sameWordAdapter = SameWordAdapter()
        suffixAdapter = SuffixAdapter()

        with(binding) {
            binding.vocalistSuffixRv.adapter = suffixAdapter
        }
    }

    private fun observeViewModel() {
        vocaListViewModel.affixItem.observe(viewLifecycleOwner) { newList ->
            affixAdapter.submitList(newList)
        }

        vocaListViewModel.sameWordItem.observe(viewLifecycleOwner) { newList ->
            sameWordAdapter.submitList(newList)
        }

        vocaListViewModel.suffixItem.observe(viewLifecycleOwner) { newList ->
            suffixAdapter.submitList(newList)
        }
    }

    private fun setSearchBar() {
        binding.vocalistSearchBar.setOnClickListener {
            val action = VocaListFragmentDirections.actionVocaListFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun setButton() {
        binding.vocalistSaveLibraryTv.setOnClickListener {
            val action = VocaListFragmentDirections.actionVocaListFragmentToLibraryFragment()
            findNavController().navigate(action)
        }
    }
}