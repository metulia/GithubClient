package com.example.githubclient.di.user.module

import com.example.githubclient.App
import com.example.githubclient.di.user.UserScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.githubclient.mvp.model.room.cache.IRoomGithubUsersCache
import com.example.githubclient.mvp.model.room.cache.RoomGithubUsersCache
import dagger.Module
import dagger.Provides

@Module
open class UserModule {

    @Provides
    fun usersCache(database: Database): IRoomGithubUsersCache =
        RoomGithubUsersCache(database)

    @UserScope
    @Provides
    fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IRoomGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(
        api, networkStatus, cache
    )

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}