package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.entity.room.dao.RoomGithubUser

class RoomGithubUsersCache(val db: Database) : IRoomGithubUsersCache {

    override fun insert(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getAll(): List<GithubUser> {
        return db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id, roomUser.login, roomUser.avatarUrl,
                roomUser.reposUrl
            )
        }
    }

}