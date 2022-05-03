package com.example.shupresentation.settings

sealed class SettingsNavigationAction {

    object OpenAppInfo : SettingsNavigationAction()

    object OpenNotificationSettings : SettingsNavigationAction()

    object OpenLicenses : SettingsNavigationAction()
}
