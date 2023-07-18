package com.example.githubclient.di.repository

import com.example.githubclient.di.repoinfo.RepoInfoSubcomponent
import com.example.githubclient.di.repository.module.RepositoryModule
import com.example.githubclient.mvp.presenter.UserPresenter
import com.example.githubclient.ui.adapter.UserRepositoriesRVAdapter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)

interface RepositorySubcomponent {

    fun repoInfoSubcomponent(): RepoInfoSubcomponent
    fun inject(userPresenter: UserPresenter)
    fun inject(userRepositoriesRVAdapter: UserRepositoriesRVAdapter)
}