package com.example.avocado_android.ui

import android.content.Context
import android.os.Build
import android.text.format.DateUtils.formatDateTime
import android.view.View
import androidx.navigation.NavController
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityMainBinding
import com.example.avocado_android.ui.home.HomeFragment
import com.example.avocado_android.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity(
) : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewModel: MainViewModel
    private var homeFragment: HomeFragment? = null

    override fun setLayout() {
        loginConfirm()
        bindingViewModel()
        setNavigation()
        setData()
    }


    private fun loginConfirm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.memberData.collectLatest { response ->
                    Log.d("response data", response.id)
                    binding.memberData = response
                    val id = response.id.takeIf { it.isNotEmpty() }?.toLong() ?: 0L

                    // id -> sharedPreferences에 저장
                    val sharedPreferences = this@MainActivity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putLong("userId", id)
                    editor.apply()



                    viewModel.getMainItemData(id, formatDateTime(LocalDateTime.now()))
                }
            }
        }
    }

    private fun formatDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return dateTime.format(formatter)
    }

    private fun setData() {
        loginViewModel.getToken { token ->
            Log.d("response datas", "Token: $token")
        }
        loginViewModel.getCookie { cookie ->
            Log.d("response datas", "cookie: $cookie")
        }
        loginViewModel.getMemberData()
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
    private fun bindingViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    // 외부 터치시 키보드 숨기기, 포커스 제거
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if (currentFocus is EditText) {
            currentFocus!!.clearFocus()
        }

        return super.dispatchTouchEvent(ev)
    }

}