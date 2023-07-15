package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.repoinfo.IRepoInfoScopeContainer
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.view.RepoInfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoInfoPresenter(private val githubRepository: GithubRepository) :
    MvpPresenter<RepoInfoView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repoInfoScopeContainer: IRepoInfoScopeContainer

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount((githubRepository.forksCount ?: 0).toString())
        viewState.setLanguage(githubRepository.language ?: "")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        repoInfoScopeContainer.releaseRepoInfoScope()
        super.onDestroy()
    }
}