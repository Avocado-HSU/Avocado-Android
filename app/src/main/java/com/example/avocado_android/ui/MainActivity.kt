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
import androidx.navigation.Navigation.findNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityMainBinding
import com.example.avocado_android.ui.home.HomeFragment
import com.example.avocado_android.ui.library.LibraryFragment
import com.example.avocado_android.ui.ChatBot.ChatBotFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var viewModel : MainViewModel
    private var homeFragment: HomeFragment? = null
    private var libraryFragment: LibraryFragment? = null
    private var chatBotFragment:ChatBotFragment? = null
    override fun setLayout() {
        setBottomNavigation()
        bindingViewModel()
        binding.mainNavBar.itemIconTintList = null
    }

    private fun setBottomNavigation() {
        // 최소 실행시 프래그먼트 설정
        binding.mainNavBar.selectedItemId = R.id.homeFragment
        homeFragment = HomeFragment()
        changeFragment(homeFragment!!)

        binding.mainNavBar.itemIconTintList = null
        binding.mainNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                        addFragment(homeFragment!!)
                    }
                    homeFragment?.let { supportFragmentManager.beginTransaction().apply { show(it).commit() } }
                    libraryFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }
                    chatBotFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }

                    return@setOnItemSelectedListener true
                }
                R.id.libraryFragment -> {
                    if (libraryFragment == null) {
                        libraryFragment = LibraryFragment()
                        addFragment(libraryFragment!!)
                    }
                    libraryFragment?.let { supportFragmentManager.beginTransaction().apply { show(it).commit() } }
                    homeFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }
                    chatBotFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }

                    return@setOnItemSelectedListener true
                }
                R.id.chatBotFragment -> {
                    if (chatBotFragment == null) {
                        chatBotFragment = ChatBotFragment()
                        addFragment(chatBotFragment!!)
                    }
                    chatBotFragment?.let { supportFragmentManager.beginTransaction().apply { show(it).commit() } }
                    homeFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }
                    libraryFragment?.let { supportFragmentManager.beginTransaction().apply { hide(it).commit() } }

                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragmentContainerView.id, fragment)
            .commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(binding.mainFragmentContainerView.id, fragment)
            .commit()
    }

    // 바텀 네비게이션 아이템의 원래 색깔 나오게
    private fun bindingViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}