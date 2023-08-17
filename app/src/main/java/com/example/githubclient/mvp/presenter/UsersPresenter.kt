package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.user.module.IUserScopeContainer
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.retrofit.IGithubUsersRepo
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.mvp.view.list.UserItemView
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter() :
    MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var userScopeContainer: IUserScopeContainer

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
    }
}