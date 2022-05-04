package com.example.renshu.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.ActivityMainBinding
import com.example.renshu.generic.BottomNavigationManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var bottomNavigationManager: BottomNavigationManager

    private val ui by viewBinding<ActivityMainBinding>()

    private val navController: NavController by lazy {
        findNavController(R.id.fragmentContainerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() {
        NavigationUI.setupWithNavController(ui.bottomNavigationView, navController)
        bottomNavigationManager.initialize()
    }
}
