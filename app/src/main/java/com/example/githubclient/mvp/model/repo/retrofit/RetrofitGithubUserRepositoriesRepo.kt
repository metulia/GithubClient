package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserRepositoriesRepo(
    private val api: IDataSource
) : IGithubUserRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>>? =
        user.reposUrl?.let { url ->
            api.getUserRepositories(url)
                .subscribeOn(
                    Schedulers.io()
                )
        }
}