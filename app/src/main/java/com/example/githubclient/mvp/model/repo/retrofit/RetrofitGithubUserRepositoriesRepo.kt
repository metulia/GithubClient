package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.model.room.cache.IRoomGithubRepositoriesCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roomGithubReposCache: IRoomGithubRepositoriesCache
) : IGithubUserRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>>? =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                roomGithubReposCache.insertRepos(user, repositories)
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(
                    RuntimeException("User has no repos url ")
                ).subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    roomGithubReposCache.findReposForUser(user)
                }
            }
        }.subscribeOn(Schedulers.io())
}