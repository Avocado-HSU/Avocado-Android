package com.example.avocado_android.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityMainBinding
import com.example.avocado_android.ui.home.HomeFragment
import com.example.avocado_android.ui.ChatBot.ChatBotFragment
import com.example.avocado_android.ui.library.LibraryFragment
import com.example.avocado_android.ui.search.SearchViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var viewModel : MainViewModel
    private lateinit var searchViewModel : SearchViewModel
    private var homeFragment: HomeFragment? = null

    override fun setLayout() {
        setNavigation()

    }

    private fun setNavigation() {

        binding.mainBottomNavigationBar.itemIconTintList = null

        val host = supportFragmentManager
            .findFragmentById(binding.mainNavHostFragment.id) as NavHostFragment ?: return
        navController = host.navController
        binding.mainBottomNavigationBar.setupWithNavController(navController)

        // 최소 실행시 프래그먼트 설정
        binding.mainBottomNavigationBar.selectedItemId = R.id.homeFragment
        navController.navigate(R.id.homeFragment)
    }

    // 뷰모델 초기화 및 연결 (MainActivity 생명주기에 맞춤)
    private fun bindingViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }
}