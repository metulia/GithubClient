package com.example.githubclient.di.repoinfo

import com.example.githubclient.mvp.presenter.RepoInfoPresenter
import dagger.Subcomponent

@RepoInfoScope
@Subcomponent(
    modules = [
        RepoInfoModule::class
    ]
)
interface RepoInfoSubcomponent {
    fun inject(repoInfoPresenter: RepoInfoPresenter)
}