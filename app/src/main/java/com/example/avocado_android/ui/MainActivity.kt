package com.example.avocado_android.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var viewModel: MainViewModel

    override fun setLayout() {
        bindingViewModel()
        binding.mainNavBar.itemIconTintList = null
    }
    // 바텀 네비게이션 아이템의 원래 색깔 나오게
    private fun bindingViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}
