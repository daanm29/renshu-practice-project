package com.example.renshu.settings.info

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.BuildConfig
import com.example.renshu.R
import com.example.renshu.databinding.FragmentAppInfoBinding
import dagger.android.support.DaggerFragment

class AppInfoFragment : DaggerFragment(R.layout.fragment_app_info) {
    
    private val ui by viewBinding<FragmentAppInfoBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.appInfoText.text = getString(R.string.app_info_text, BuildConfig.VERSION_NAME)
        initToolbar()
    }

    private fun initToolbar() {
        ui.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
