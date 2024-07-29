package com.example.avocado_android.ui.wordList

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentWordListBinding
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import com.example.avocado_android.ui.search.SearchViewModel
import com.example.avocado_android.ui.vocalist.PrefixAdapter
import com.example.avocado_android.ui.vocalist.EtymologyAdapter
import com.example.avocado_android.ui.vocalist.SuffixAdapter
import com.example.avocado_android.utils.enunm.ResponseType
import com.example.avocado_android.utils.extensions.HorizontalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordListFragment : BaseFragment<FragmentWordListBinding>(R.layout.fragment_word_list) {

    private val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(0)
    private val args: WordListFragmentArgs by navArgs()

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var prefixAdapter: PrefixAdapter
    private lateinit var suffixAdapter: SuffixAdapter
    private lateinit var etymologyAdapter: EtymologyAdapter

    private var originalLibraryId: Long = 0

    override fun setLayout() {
        setAdapter()
        observeViewModel()
        setSearchBar()
        setLibraryButton()
    }

    // 라이브러리 저장/삭제 버튼 클릭하면 저장/삭제 수행
    private fun setLibraryButton() {
        binding.wordListSaveLibraryBtn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.updateLibrary(getLibraryId())
                viewModel.updateLibraryResponseDto.collectLatest { updateLibraryResponseDto ->
                    when (updateLibraryResponseDto.responseType) {
                        ResponseType.REGISTERED -> setLibraryButton(true) // 라이브러리에 저장된 상태
                        ResponseType.DELETED -> setLibraryButton(false)      // 라이브러리에서 삭제된 상태
                        //ResponseType.ERROR ->
                        else -> true
                    }
                }
            }
        }
    }

    // T/F 값에 따라 라이브러리 저장/삭제 버튼 UI 설정
    private fun setLibraryButton(state: Boolean) {
        val wordListSaveLibraryBtn = binding.wordListSaveLibraryBtn
        if(state) {
            wordListSaveLibraryBtn.background =  ContextCompat.getDrawable(requireContext(), R.drawable.rounded_rectangle_gr1_300_50dp)
            wordListSaveLibraryBtn.text = "라이브러리에서 삭제하기"
            wordListSaveLibraryBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green_2_500))
        } else {
            wordListSaveLibraryBtn.background =  ContextCompat.getDrawable(requireContext(), R.drawable.rounded_rectangle_gr2_500_50dp)
            wordListSaveLibraryBtn.text = "라이브러리에 저장하기"
            wordListSaveLibraryBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.Yellow))
        }
    }

    // 서버에서 온 값 파싱하여 값에 따라 화면 UI 업데이트
    private fun updateUI(data: SearchWordResponseDto) {

        val wordTipsTv = binding.wordListEasyMemorizeWordTipTv

        // 단어 부분은 검색 프래그먼트에서 safeArgs로 받음
        binding.wordListWordTv.text = args.word
        binding.wordListWordMeaningEngWord.text = args.word

        // 라이브러리에 단어 없을 때 (저장하기)
        if (data.isLibraryRegistered == false) { setLibraryButton(false) }
        else { setLibraryButton(true) }

        // 영어 단어 한글 뜻
        binding.wordListWordMeaningKorWord.text = data.korean ?: "null"

        // 이미지 설정
        val imgUrl = data.characterImgUrl.toString()
        Glide.with(requireContext())
            .load(imgUrl)
            .into(binding.wordListLogoImgIv)

        // 단어적 의미, 어원 분리, 쉽게 외우는 팁 파싱
        data.contents?.let { contents ->

            // 단어적 의미 -> 데이터 바인딩 사용하면 혼자만 값이 빨리 나와서 직접 바인딩
            val meaningsText = contents.wordMeanDto?.meanings
                ?.entries
                ?.filter { it.value.isNotBlank() }  // 값이 비어있지 않은 항목만 필터링
                ?.joinToString(separator = "\n") { "${it.key}: ${it.value}" }  // 필터링된 항목으로 문자열 생성
                ?: ""  // 모든 항목이 비어있으면 빈 문자열
            binding.wordListWordMeaningDescription.text = meaningsText

            // 쉽게 외우는 팁
            val wordTipsDto = contents.wordTipsDto

            val wordTipsList = mutableListOf<String>()
            wordTipsDto?.mnemonicMethod?.takeIf { it.isNotBlank() }?.let { wordTipsList.add(it) }
            wordTipsDto?.wordAnalysis?.takeIf { it.isNotBlank() }?.let { wordTipsList.add(it) }
            wordTipsDto?.spacedRepetition?.takeIf { it.isNotBlank() }?.let { wordTipsList.add(it) }
            wordTipsDto?.storytelling?.takeIf { it.isNotBlank() }?.let { wordTipsList.add(it) }
            val wordTipsDesc = wordTipsList.joinToString(separator = "\n\n")

            binding.wordListEasyMemorizeWordTipTv.text = wordTipsDesc

            // 어원 분리
            val wordEtymologyDto = contents.wordEtymologyDto
            val rootString = buildString {
                wordEtymologyDto?.root?.takeIf { it.isNotBlank() }?.let { append("어간 (Root): $it") }
                wordEtymologyDto?.rootDescription?.takeIf { it.isNotBlank() }?.let {
                    if (isNotEmpty()) append("\n") // `root`가 이미 추가된 경우 줄바꿈 추가
                    append(it)
                }
            }.trim()

            val prefixString = buildString {
                wordEtymologyDto?.prefix?.takeIf { it.isNotBlank() }?.let { append("접두사 (Prefix): $it") }
                wordEtymologyDto?.prefixDescription?.takeIf { it.isNotBlank() }?.let {
                    if (isNotEmpty()) append("\n") // `prefix`가 이미 추가된 경우 줄바꿈 추가
                    append(it)
                }
            }.trim()

            val suffixString = buildString {
                wordEtymologyDto?.suffix?.takeIf { it.isNotBlank() }?.let { append("접미사 (Suffix): $it") }
                wordEtymologyDto?.suffixDescription?.takeIf { it.isNotBlank() }?.let {
                    if (isNotEmpty()) append("\n") // `suffix`가 이미 추가된 경우 줄바꿈 추가
                    append(it)
                }
            }.trim()

            val etymologyDescription = wordEtymologyDto?.etymologyDescription?.takeIf { it.isNotBlank() } ?: ""

            // 최종 어원 문자열 생성
            val combinedString = listOf(
                rootString,
                prefixString,
                suffixString,
                etymologyDescription
            ).filter { it.isNotEmpty() }.joinToString(separator = "\n\n")

            binding.wordListSuffixDescTv.text = combinedString

        }
    }

    private fun setAdapter() {
        etymologyAdapter = EtymologyAdapter()
        prefixAdapter = PrefixAdapter()
        suffixAdapter = SuffixAdapter()

        with(binding) {
            binding.wordEtymologyRv.adapter = etymologyAdapter
            binding.wordListPrefixRv.adapter = prefixAdapter
            binding.wordListSuffixRv.adapter = suffixAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchWordResponseDto.collect { searchWordResponseDto ->
                    if (searchWordResponseDto.isSuccess == true) {
                        etymologyAdapter.submitList(listOf(searchWordResponseDto.contents?.wordEtymologyDto))
                        searchWordResponseDto.libraryId?.let { saveLibraryId(it) }
                    }
                    searchWordResponseDto.contents?.wordEtymologyDto?.let { viewModel.setWordEtymologyDto(it) }
                    updateUI(searchWordResponseDto)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wordEtymologyDto.collect { wordEtymologyDto ->
                    prefixAdapter.submitList(listOf(wordEtymologyDto))
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wordEtymologyDto.collect { wordEtymologyDto ->
                    suffixAdapter.submitList(listOf(wordEtymologyDto))
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wordEtymologyDto.collect { wordEtymologyDto ->
                    etymologyAdapter.submitList(listOf(wordEtymologyDto))
                }
            }
        }
    }

    // 검색 창 누르면 검색 화면으로 이동
    private fun setSearchBar() {
        binding.wordListSearchBar.setOnClickListener {
            val action = WordListFragmentDirections.actionWordListFragmentToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun saveLibraryId(libraryId: Long) {
        originalLibraryId = libraryId
    }
    private fun getLibraryId() : Long {
        Log.d("originalLibraryId", "originalLibraryId: $originalLibraryId")
        return originalLibraryId
    }

}