package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubUsersCache {

    fun insert(users: List<GithubUser>): Completable
    fun getAll() : Single<List<GithubUser>>
}