package com.example.githubclient.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {

    fun fromIterable(): Observable<GithubUser> {
        return Observable.fromIterable(
            listOf(
                GithubUser("login1"),
                GithubUser("login2"),
                GithubUser("login3"),
                GithubUser("login4"),
                GithubUser("login5")
            )
        )
    }

}