package com.example.githubclient.di.module

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.room.cache.IRoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.room.cache.IRoomGithubUsersCache
import com.example.githubclient.mvp.model.room.cache.RoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.room.cache.RoomGithubUsersCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java, Database.DB_NAME
    )
        .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IRoomGithubUsersCache =
        RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRoomGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)
}