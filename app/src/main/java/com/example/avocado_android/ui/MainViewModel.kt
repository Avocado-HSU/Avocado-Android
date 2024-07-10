package com.example.avocado_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avocado_android.R
import com.example.avocado_android.domain.model.HomeDaysItem
import com.example.avocado_android.domain.model.HomeRecommendedItem

class MainViewModel : ViewModel() {
    private val _checkedDayItems = MutableLiveData<List<HomeDaysItem>>()
    val homeDaysItem: LiveData<List<HomeDaysItem>> get() = _checkedDayItems

    private val _bestChoiceItem = MutableLiveData<List<String>>()
    val bestChoiceItem: LiveData<List<String>> get() = _bestChoiceItem

    private val _recommendedItem = MutableLiveData<List<HomeRecommendedItem>>()
    val recommendedItem: LiveData<List<HomeRecommendedItem>> get() = _recommendedItem


    init {
        // 초기 데이터 설정
        _checkedDayItems.value = listOf(
            HomeDaysItem(0, "MON", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(1, "TUE", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(2, "WED", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(3, "THU", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(4, "FRI", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(5, "SAT", R.drawable.ic_circle_days_32dp),
            HomeDaysItem(6, "SUN", R.drawable.ic_circle_days_32dp),
        )


        _bestChoiceItem.value = listOf(
            "Technology",
            "Nature",
            "Food",
            "Responsive"
        )

        _recommendedItem.value = listOf(
            HomeRecommendedItem(0,"Iconic","상징적인","icon(상징) + -ic(~의, ~적인)"),
            HomeRecommendedItem(1,"Friendship","우정","Friend(친구) + -ship(~관계, ~상태)"),
            HomeRecommendedItem(2,"Childhood","어린시절","Child(아이) + -hood(~기, ~상태)"),
            HomeRecommendedItem(3,"Childhood","어린시절","Child(아이) + -hood(~기, ~상태)")
        )

    }

    fun updateData(pos: Int, newData: HomeDaysItem) {
        _checkedDayItems.value = _checkedDayItems.value?.mapIndexed { index, item ->
            if (index == pos) newData else item
        }
    }
}