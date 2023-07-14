package com.example.githubclient.di.repository.module

import com.example.githubclient.App
import com.example.githubclient.di.repository.RepositoryScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.retrofit.IGithubUserRepositoriesRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUserRepositoriesRepo
import com.example.githubclient.mvp.model.room.cache.IRoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.room.cache.RoomGithubRepositoriesCache
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IRoomGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IRoomGithubRepositoriesCache
    ): IGithubUserRepositoriesRepo = RetrofitGithubUserRepositoriesRepo(
        api, networkStatus, cache
    )

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}