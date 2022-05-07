package com.example.shupresentation.home

sealed class HomeNavigationAction {

    object OpenHiraganaPractice : HomeNavigationAction()

    object OpenRecentKatakana : HomeNavigationAction()

    object OpenRecentListPractice : HomeNavigationAction()
}
