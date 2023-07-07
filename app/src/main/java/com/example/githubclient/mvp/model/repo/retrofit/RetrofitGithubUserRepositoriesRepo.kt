package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.dao.Database
import com.example.githubclient.mvp.model.entity.room.dao.RoomGithubRepository
import com.example.githubclient.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserRepositoriesRepo(
    private val api: IDataSource, val networkStatus: INetworkStatus, val db: Database
) : IGithubUserRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>>? =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
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
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(
                    RuntimeException("User has no repos url ")
                ).subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).map {
                        GithubRepository(it.id, it.name, it.forksCount)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}