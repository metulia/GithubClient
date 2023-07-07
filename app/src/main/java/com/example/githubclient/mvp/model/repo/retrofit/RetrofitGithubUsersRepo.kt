package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.model.room.cache.IRoomGithubUsersCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    val networkStatus: INetworkStatus,
    val roomGithubUsersCache: IRoomGithubUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    roomGithubUsersCache.insert(users)
                    users
                }
            }
        } else {
            Single.fromCallable {
                roomGithubUsersCache.getAll()
            }
        }
    }.subscribeOn(Schedulers.io())
}