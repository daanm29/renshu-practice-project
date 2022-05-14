package com.example.renshu.practice.list

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentOverviewListBinding
import dagger.android.support.DaggerFragment

class ListOverviewFragment : DaggerFragment(R.layout.fragment_overview_list) {

    private val ui by viewBinding<FragmentOverviewListBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
