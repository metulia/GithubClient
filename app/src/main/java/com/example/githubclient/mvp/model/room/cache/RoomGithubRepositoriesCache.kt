package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.entity.room.dao.RoomGithubRepository

class RoomGithubRepositoriesCache(val db: Database) : IRoomGithubRepositoriesCache {

    override fun insertRepos(user: GithubUser, repositories: List<GithubRepository>) {

        val roomUser = user.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
    }

    override fun findReposForUser(user: GithubUser): List<GithubRepository> {

        val roomUser = user.login?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        return db.repositoryDao.findForUser(roomUser.id).map {
            GithubRepository(it.id, it.name, it.forksCount)
        }
    }

}