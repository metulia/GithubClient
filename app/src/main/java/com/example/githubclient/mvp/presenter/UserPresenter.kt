package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}