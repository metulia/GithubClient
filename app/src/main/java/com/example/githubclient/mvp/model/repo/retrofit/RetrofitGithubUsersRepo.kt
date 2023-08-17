package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.room.cache.IRoomGithubUsersCache
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roomGithubUsersCache: IRoomGithubUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                roomGithubUsersCache.insert(users).toSingleDefault(users)
            }
        } else {
            roomGithubUsersCache.getAll()
        }
    }.subscribeOn(Schedulers.io())
}