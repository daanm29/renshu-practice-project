package com.example.renshu.generic

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.example.renshu.R
import com.example.renshu.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BottomNavigationManager @Inject constructor(private val mainActivity: MainActivity) {

    private val navController: NavController by lazy {
        Navigation.findNavController(mainActivity.findViewById(R.id.fragmentContainerView))
    }

    private val withoutBottomBar: Set<Int> = setOf(
        R.id.fragment_app_info,
        R.id.fragment_alphabet,
        R.id.fragment_kana_exercise,
        R.id.fragment_list_overview,
        R.id.fragment_list_add,
    )

    fun initialize(onBottomBarShown: ((Boolean) -> Unit)? = null) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            update(destination)
            onBottomBarShown?.invoke(destination.id !in withoutBottomBar)
        }
    }

    private fun update(destination: NavDestination) {
        val bottomNavigationView = mainActivity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        if (destination.id in withoutBottomBar) {
            bottomNavigationView.isGone = true
        } else {
            bottomNavigationView.isVisible = true
        }
    }
}
