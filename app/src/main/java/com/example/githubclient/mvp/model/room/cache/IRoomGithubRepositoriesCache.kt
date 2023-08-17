package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoriesCache {

    fun insertRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
    fun findReposForUser(user: GithubUser) : Single <List<GithubRepository>>
}