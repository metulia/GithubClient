package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>?
}