package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.entity.room.dao.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database) : IRoomGithubUsersCache {

    override fun insert(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getAll() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id, roomUser.login, roomUser.avatarUrl,
                roomUser.reposUrl
            )
        }
    }.subscribeOn(Schedulers.io())
}