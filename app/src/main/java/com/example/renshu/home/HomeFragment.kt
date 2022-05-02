package com.example.renshu.home

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment

class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    private val ui by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
