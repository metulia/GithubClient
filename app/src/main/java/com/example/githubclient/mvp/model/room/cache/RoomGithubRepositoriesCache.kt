package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.entity.room.dao.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(val db: Database) : IRoomGithubRepositoriesCache {

    override fun insertRepos(user: GithubUser, repositories: List<GithubRepository>) =
        Completable.fromAction {

            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(
                    it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())

    override fun findReposForUser(user: GithubUser) = Single.fromCallable {

        val roomUser =
            db.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache")
        return@fromCallable db.repositoryDao.findForUser(roomUser.id).map {
            GithubRepository(it.id, it.name, it.forksCount)
        }
    }.subscribeOn(Schedulers.io())

}