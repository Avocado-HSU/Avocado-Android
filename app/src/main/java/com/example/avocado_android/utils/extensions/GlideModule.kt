package com.example.avocado_android.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

// Glide -> GlideApp로 사용
@GlideModule
class GlideModule: AppGlideModule() {
    @BindingAdapter(value = ["loadUrl"])
    fun ImageView.loadUrl(url: String?) {
        url?.let {
            Glide.with(this.context)
                .load(url)
                .into(this)
        }
    }
    @BindingAdapter("drawableRes")
    fun ImageView.setDrawableRes(drawableRes: Int?) {
        drawableRes?.let {
            this.setImageResource(it)
        }
    }

}