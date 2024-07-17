package com.example.avocado_android.ui

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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

        // 챗봇 프래그먼트 툴바, 바텀네비게이션 제거
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.chatBotFragment) {
                binding.mainTb.visibility = View.GONE
                binding.mainTb.setOnClickListener(null)
                binding.mainBottomNavigationBar.visibility = View.GONE
            } else {
                binding.mainTb.visibility = View.VISIBLE
                binding.mainBottomNavigationBar.visibility = View.VISIBLE
            }
        }
    }

    // 뷰모델 초기화 및 연결 (MainActivity 생명주기에 맞춤)
    private fun bindingViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    // 외부 터치시 키보드 숨기기, 포커스 제거
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if(currentFocus is EditText) {
            currentFocus!!.clearFocus()
        }

        return super.dispatchTouchEvent(ev)
    }

}