package com.example.renshu.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentSettingsBinding
import com.example.shupresentation.settings.SettingsNavigationAction
import com.example.shupresentation.settings.SettingsNavigationAction.*
import com.example.shupresentation.settings.SettingsViewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingsFragment : DaggerFragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentSettingsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initButtons()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigationAction)
    }

    private fun initButtons() {
        ui.appInfoButton.setOnClickListener { viewModel.onOpenAppInfo() }
        ui.notificationSettingButton.setOnClickListener { viewModel.onOpenNotificationSettings() }
        ui.licenseButton.setOnClickListener { viewModel.onOpenLicenses() }
    }

    private fun handleNavigationAction(action: SettingsNavigationAction) {
        when (action) {
            OpenAppInfo -> openAppInfo()
            OpenLicenses -> openLicenses()
            OpenNotificationSettings -> openNotificationSettings()
        }
    }

    private fun openAppInfo() {
        findNavController()
            .navigate(SettingsFragmentDirections.actionSettingsFragmentToAppInfoFragment())
    }

    private fun openLicenses() {
        requireActivity().startActivity(Intent(activity, OssLicensesMenuActivity::class.java))
    }

    private fun openNotificationSettings() {
        startActivity(Intent().apply {
            action = NOTIFICATION_SETTINGS
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                putExtra(PACKAGE_PROVIDER, requireActivity().packageName)
            } else {
                putExtra(PACKAGE, requireActivity().packageName)
                putExtra(UID, requireActivity().applicationInfo.uid)
            }
        })
    }

    companion object {

        private const val NOTIFICATION_SETTINGS = "android.settings.APP_NOTIFICATION_SETTINGS"
        private const val PACKAGE_PROVIDER = "android.provider.extra.APP_PACKAGE"
        private const val PACKAGE = "app_package"
        private const val UID = "app_uid"
    }
}
