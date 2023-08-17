package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.ui.fragment.RepoInfoFragment
import com.example.githubclient.ui.fragment.UserFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser): Screen = FragmentScreen { UserFragment.newInstance(user) }
    override fun repoInfo(repo: GithubRepository): Screen =
        FragmentScreen { RepoInfoFragment.newInstance(repo) }
}