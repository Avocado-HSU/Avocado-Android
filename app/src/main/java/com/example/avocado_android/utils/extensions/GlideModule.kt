package com.example.avocado_android.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule()

object GlideBindingAdapters {

    @JvmStatic
    @BindingAdapter("loadUrl")
    fun loadUrl(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("drawableRes")
    fun setDrawableRes(view: ImageView, drawableRes: Int?) {
        drawableRes?.let {
            view.setImageResource(it)
        }
    }
}
