package com.example.renshu.practice

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentPracticeBinding
import dagger.android.support.DaggerFragment

class PracticeFragment : DaggerFragment(R.layout.fragment_practice) {

    private val ui by viewBinding<FragmentPracticeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
