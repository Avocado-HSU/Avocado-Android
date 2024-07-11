package com.example.avocado_android.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentHomeBinding
import com.example.avocado_android.ui.MainViewModel
import com.example.avocado_android.ui.search.SearchFragment
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home)  {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var checkDaysAdapter: CheckDaysAdapter
    private lateinit var bestChoiceAdapter: BestChoiceAdapter
    private lateinit var recommendDataAdapter: RecommendedWordAdapter
    private val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(23)
    override fun setLayout() {
        initAdapter()
        setSearchBar()
    }

    private fun initAdapter() {
        checkDaysAdapter = CheckDaysAdapter()
        bestChoiceAdapter = BestChoiceAdapter()
        recommendDataAdapter = RecommendedWordAdapter()

        with(binding) {
            homeDaysRv.adapter = checkDaysAdapter
            homeDaysRv.addItemDecoration(HorizontalSpaceItemDecoration(30))
            homeBestWordRv.adapter = bestChoiceAdapter
            homeWordCardRv.adapter = recommendDataAdapter
        }

        viewModel.homeDaysItem.observe(viewLifecycleOwner) { newList ->
            checkDaysAdapter.submitList(newList)
        }
        viewModel.bestChoiceItem.observe(viewLifecycleOwner) { newList ->
            bestChoiceAdapter.submitList(newList)
        }
        viewModel.recommendedItem.observe(viewLifecycleOwner){newList ->
            recommendDataAdapter.submitList(newList)
        }

    }

    private fun setSearchBar() {
        binding.homeSearchHereEt.setOnClickListener {
           val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}