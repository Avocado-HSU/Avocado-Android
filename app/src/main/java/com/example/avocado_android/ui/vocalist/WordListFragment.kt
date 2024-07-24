package com.example.avocado_android.ui.wordList

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentWordListBinding
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.ui.search.SearchViewModel
import com.example.avocado_android.ui.vocalist.AffixAdapter
import com.example.avocado_android.utils.enunm.SearchRequestType
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordListFragment : BaseFragment<FragmentWordListBinding>(R.layout.fragment_word_list) {

    private val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(0)
    private val args: WordListFragmentArgs by navArgs()

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var affixAdapter: AffixAdapter

    override fun setLayout() {
        setAdapter()
        observeViewModel()
        setSearchBar()
        setButton()
    }

    // 서버에서 온 값 파싱하여 값에 따라 화면 UI 업데이트
    private fun updateUI(data: SearchWordResponseDto) {

        // 단어 부분은 검색 프래그먼트에서 safeArgs로 받음
        binding.wordListWordTv.text = args.word
        binding.wordListWordMeaningEngWord.text = args.word

        // 라이브러리 저장 버튼 UI 변경
        if (data.isLibraryRegistered == false) {
            binding.wordListSaveLibraryTv.background =  ContextCompat.getDrawable(requireContext(), R.drawable.rounded_rectangle_gr2_500_50dp)
            binding.wordListSaveLibraryTv.text = "라이브러리에 저장하기"
            binding.wordListSaveLibraryTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.Yellow))
        } else {
            binding.wordListSaveLibraryTv.background =  ContextCompat.getDrawable(requireContext(), R.drawable.rounded_rectangle_gr1_300_50dp)
            binding.wordListSaveLibraryTv.text = "라이브러리에서 삭제하기"
            binding.wordListSaveLibraryTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green_2_500))
        }

        // 이미지 설정
        val imgUrl = data.characterImgUrl.toString()
        Glide.with(requireContext())
            .load(imgUrl)
            .into(binding.wordListLogoImgIv)

        // 단어적 의미, 어원 분리, 쉽게 외우는 팁 파싱
        data.contents?.let { contents ->
            binding.wordListWordMeaningDescription.text = ""
            binding.wordListSuffixDescTv.text = ""
            binding.wordListEasyMemorizeWordTipTv.text = ""

            // contents의 각 항목을 UI 요소에 매핑
            contents.forEach { (type, value) ->
                when (type) {
                    SearchRequestType.MEANS -> {
                        binding.wordListWordMeaningDescription.text = value
                    }

                    SearchRequestType.SEPARATE -> {
                        binding.wordListSuffixDescTv.text = value
                    }

                    SearchRequestType.TIP -> {
                        binding.wordListEasyMemorizeWordTipTv.text = value
                    }
                }
            }
        }
    }


    private fun setAdapter() {
        affixAdapter = AffixAdapter()

        with(binding) {
            binding.wordListSuffixRv.adapter = affixAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.searchWordResponseDto.collect { wordResponseDto ->
                if (wordResponseDto.isSuccess == true) {
                    affixAdapter.submitList(listOf(wordResponseDto))
                    updateUI(wordResponseDto)
                }
            }
        }
    }

    // 검색 창 누르면 검색 화면으로 이ㅏ동
    private fun setSearchBar() {
        binding.wordListSearchBar.setOnClickListener {
            val action = WordListFragmentDirections.actionWordListFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

    // 라이브리에 저장하기 버튼
    private fun setButton() {
        binding.wordListSaveLibraryTv.setOnClickListener {
            val action = WordListFragmentDirections.actionWordListFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }
}