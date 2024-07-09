package com.example.avocado_android.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityMainBinding
import com.example.avocado_android.ui.home.HomeFragment
import com.example.avocado_android.ui.library.LibraryFragment
import com.example.avocado_android.ui.quiz.QuizFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    private var homeFragment: HomeFragment? = null
    private var libraryFragment: LibraryFragment? = null
    private var quizFragment:QuizFragment? = null

    override fun setLayout() {
        setBottomNavigation()
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
                    if (homeFragment != null) supportFragmentManager.beginTransaction().apply { show(homeFragment!!).commit() }
                    if (libraryFragment != null) supportFragmentManager.beginTransaction().apply { hide(libraryFragment!!).commit() }
                    if (quizFragment != null) supportFragmentManager.beginTransaction().apply { hide(quizFragment!!).commit() }
                    return@setOnItemSelectedListener true
                }
                R.id.libraryFragment -> {
                    if (libraryFragment == null) {
                        libraryFragment = LibraryFragment()
                        addFragment(libraryFragment!!)
                    }
                    if (libraryFragment != null) supportFragmentManager.beginTransaction().apply { show(libraryFragment!!).commit() }
                    if (homeFragment != null) supportFragmentManager.beginTransaction().apply { hide(homeFragment!!).commit() }
                    if (quizFragment != null) supportFragmentManager.beginTransaction().apply { hide(quizFragment!!).commit() }
                    return@setOnItemSelectedListener true
                }
                R.id.quizFragment -> {
                    if (quizFragment == null) {
                        quizFragment = QuizFragment()
                        addFragment(quizFragment!!)
                    }
                    if (quizFragment != null) supportFragmentManager.beginTransaction().apply { show(quizFragment!!).commit() }
                    if (homeFragment != null) supportFragmentManager.beginTransaction().apply { hide(homeFragment!!).commit() }
                    if (libraryFragment != null) supportFragmentManager.beginTransaction().apply { hide(libraryFragment!!).commit() }
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

}