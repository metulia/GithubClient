package com.example.githubclient.mvp.model.room.cache

import com.example.githubclient.mvp.model.entity.GithubUser

interface IRoomGithubUsersCache {

    fun insert(users: List<GithubUser>)
    fun getAll() : List<GithubUser>
}