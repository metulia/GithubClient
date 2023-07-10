package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser

interface IRoomGithubRepositoriesCache {

    fun insertRepos(user: GithubUser, repositories: List<GithubRepository>)
    fun findReposForUser(user: GithubUser) : List<GithubRepository>
}