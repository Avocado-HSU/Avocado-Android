package com.example.avocado_android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.R
import com.example.avocado_android.domain.model.local.home.HomeDaysItem
import com.example.avocado_android.domain.model.local.home.HomeRecommendedItem
import com.example.avocado_android.domain.model.local.library.LibraryWordCard
import com.example.avocado_android.domain.model.request.MainPageRequestDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.main.PopularWordDto
import com.example.avocado_android.domain.model.response.main.RecommendWordDto
import com.example.avocado_android.domain.repository.mainpage.MainPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainPageRepository: MainPageRepository
) : ViewModel() {
    private val _checkedDayItems = MutableLiveData<List<HomeDaysItem>>()
    val homeDaysItem: LiveData<List<HomeDaysItem>> get() = _checkedDayItems

    private val _bestChoiceItem = MutableStateFlow(PopularWordDto())
    val bestChoiceItem: StateFlow<PopularWordDto> get() = _bestChoiceItem

    private val _recommendedItem = MutableStateFlow(RecommendWordDto())
    val recommendedItem: StateFlow<RecommendWordDto> get() = _recommendedItem

    private val _libraryWordCardItem = MutableLiveData<List<LibraryWordCard>>()
    val libraryWordCardItem: LiveData<List<LibraryWordCard>> get() = _libraryWordCardItem

    private val _mainPageItem = MutableStateFlow(MainPageResponseDto())
    val mainPageItem: StateFlow<MainPageResponseDto> = _mainPageItem

    fun getMainItemData(id: Long, date: String) {
        viewModelScope.launch {
            try {
                mainPageRepository.getMainPage(id, MainPageRequestDto(date)).collect {
                    _mainPageItem.value = it
                }
            } catch (e: Exception) {
                Log.e("Get MemberData is Error", e.message.toString())
            }
        }
    }

    fun updateStamp(pos: Int, stamp: Boolean) {
        val item = _checkedDayItems.value?.get(pos)
        item?.state = stamp
        Log.d("아이템", stamp.toString())
        _checkedDayItems.postValue(_checkedDayItems.value) // 리스트 전체를 갱신
    }

    fun setBestChoiceItem(popularWordDto: PopularWordDto) {
        _bestChoiceItem.value = popularWordDto
    }
    fun setRecommendItem(recommendWordDto: RecommendWordDto) {
        _recommendedItem.value = recommendWordDto
    }
    init {
        // 초기 데이터 설정
        _checkedDayItems.value = listOf(
            HomeDaysItem(0, "MON", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(1, "TUE", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(2, "WED", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(3, "THU", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(4, "FRI", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(5, "SAT", false, R.drawable.ic_circle_days_32dp),
            HomeDaysItem(6, "SUN", false, R.drawable.ic_circle_days_32dp),
        )




        _libraryWordCardItem.value = listOf(
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
            LibraryWordCard("Iconic", "상징적인", "icon(상징)", "-ic(~의, ~적인)"),
        )
    }
}
