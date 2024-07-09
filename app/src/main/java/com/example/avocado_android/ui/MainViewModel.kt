package com.example.avocado_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avocado_android.domain.model.HomeDaysItem

class MainViewModel : ViewModel() {
    private val _checkedDayItems = MutableLiveData<List<HomeDaysItem>>()
    val homeDaysItem: LiveData<List<HomeDaysItem>> get() = _checkedDayItems

    init {
        // 초기 데이터 설정
        _checkedDayItems.value = listOf(
            HomeDaysItem(0, "MON", null),
            HomeDaysItem(1, "TUE", null),
            HomeDaysItem(2, "WED", null),
            HomeDaysItem(3, "THU", null),
            HomeDaysItem(4, "FRI", null),
            HomeDaysItem(5, "SAT", null),
            HomeDaysItem(6, "SUN", null),
        )
    }

    fun updateData(pos: Int, newData: HomeDaysItem) {
        _checkedDayItems.value = _checkedDayItems.value?.mapIndexed { index, item ->
            if (index == pos) newData else item
        }
    }
}