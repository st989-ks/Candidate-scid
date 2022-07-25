package com.pipe.listapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pipe.listapplication.R
import com.pipe.listapplication.di.NavigatorModule
import com.pipe.listapplication.extensions.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorModule: NavigatorModule

    @Inject
    lateinit var sharedPref: SharedPrefs

    private lateinit var navHostFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref.clearAfter()
        setContentView(R.layout.activity_main)
        initNavHostFragment()
    }

    private fun initNavHostFragment(){
        navHostFragment =
            requireNotNull(supportFragmentManager.findFragmentById(R.id.fragmentContainer))
        val navController = navHostFragment.findNavController()
        navigatorModule.navController = navController
    }

    override fun onBackPressed() {
        if (navHostFragment.childFragmentManager.backStackEntryCount > 0) {
            navigatorModule.navigateBack()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        sharedPref.clearAfter()
        super.onDestroy()
    }
}