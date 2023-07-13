package com.example.githubclient.di.module

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.model.repo.retrofit.IGithubUserRepositoriesRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUserRepositoriesRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.githubclient.mvp.model.room.cache.IRoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.room.cache.IRoomGithubUsersCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IRoomGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(
        api, networkStatus, cache
    )

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IRoomGithubRepositoriesCache
    ): IGithubUserRepositoriesRepo = RetrofitGithubUserRepositoriesRepo(
        api, networkStatus, cache
    )
}