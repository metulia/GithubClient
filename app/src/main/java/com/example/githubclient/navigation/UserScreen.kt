package com.example.githubclient.navigation

import com.example.githubclient.ui.fragment.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen : IScreen {
    override fun user() = FragmentScreen { UserFragment.newInstance() }
}