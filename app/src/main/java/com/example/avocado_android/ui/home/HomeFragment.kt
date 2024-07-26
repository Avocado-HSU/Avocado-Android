package com.example.avocado_android.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentHomeBinding
import com.example.avocado_android.ui.MainViewModel
import com.example.avocado_android.ui.login.LoginViewModel
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home)  {
    private val viewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var checkDaysAdapter: CheckDaysAdapter
    private lateinit var bestChoiceAdapter: BestChoiceAdapter
    private lateinit var recommendDataAdapter: RecommendedWordAdapter

    override fun setLayout() {
        initAdapter()
        setSearchBar()
        loginConfirm()
        mainSetting()
    }

    private fun loginConfirm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.memberData.collectLatest { response ->
                    Log.d("response data", response.id)
                    binding.memberData = response
                    val id = response.id.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
                    viewModel.getMainItemData(id, formatDateTime(LocalDateTime.now()))
                }
            }
        }
    }

    private fun formatDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return dateTime.format(formatter)
    }

    private fun mainSetting() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.mainPageItem.collectLatest { mainData ->
                    binding.mainData = mainData
                    with(mainData) {
                        weeklyAttendanceDto.attendances.forEachIndexed { index, value ->
                            viewModel.updateStamp(index, value)
                        }
                        viewModel.setBestChoiceItem(popularWordDto)
                        viewModel.setRecommendItem(recommendWordDto)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bestChoiceItem.collectLatest { popularWordDto ->
                    bestChoiceAdapter.submitList(popularWordDto.popularWords) }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recommendedItem.collectLatest { recommendDto ->
                    recommendDataAdapter.submitList(recommendDto.recommendWords)
                }
            }
        }
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

    }

    private fun setSearchBar() {
        binding.homeSearchHereEt.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}
