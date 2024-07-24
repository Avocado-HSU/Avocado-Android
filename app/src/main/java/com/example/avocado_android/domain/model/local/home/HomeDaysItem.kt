package com.example.avocado_android.domain.model.local.home
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.avocado_android.BR
import com.example.avocado_android.R

data class HomeDaysItem(
    val id: Int,
    val day: String,
    private var _state: Boolean,
    private var _imageRes: Int = R.drawable.ic_circle_days_32dp
) : BaseObservable() {

    var state: Boolean
        @Bindable get() = _state
        set(value) {
            if (_state != value) {
                _state = value
                updateImage()
                notifyPropertyChanged(BR.state)
            }
        }

    var imageRes: Int
        @Bindable get() = _imageRes
        set(value) {
            if (_imageRes != value) {
                _imageRes = value
                notifyPropertyChanged(BR.imageRes)
            }
        }

    private fun updateImage() {
        imageRes = if (state) {
            R.drawable.ic_check_day // 상태가 true일 때의 이미지 리소스
        } else {
            R.drawable.ic_circle_days_32dp // 상태가 false일 때의 이미지 리소스
        }
    }
}
