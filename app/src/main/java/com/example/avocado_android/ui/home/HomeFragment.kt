package com.example.avocado_android.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentHomeBinding
import com.example.avocado_android.ui.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home)  {
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var adapter: CheckDaysAdapter
    override fun setLayout() {
        initAdapter()
    }
    private fun initAdapter() {
        adapter = CheckDaysAdapter()
        binding.homeDaysRv.adapter = adapter
        viewModel.homeDaysItem.observe(viewLifecycleOwner) { newList ->
            adapter.submitList(newList)
        }
    }
}