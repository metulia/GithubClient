package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.user.module.IUserScopeContainer
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.retrofit.IGithubUserRepositoriesRepo
import com.example.githubclient.mvp.presenter.list.IUserRepositoryListPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.list.UserRepositoryItemView
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(
    private val user: GithubUser
) :
    MvpPresenter<UserView>() {

    @Inject
    lateinit var repositoriesRepo: IGithubUserRepositoriesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens
    @Inject
    lateinit var uiScheduler: Scheduler
    @Inject
    lateinit var repositoryScopeContainer: IUserScopeContainer

    class UserRepositoriesListPresenter : IUserRepositoryListPresenter {

        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((UserRepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: UserRepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val userRepositoriesListPresenter = UserRepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        user.login?.let { viewState.setUserLogin(it) }

        userRepositoriesListPresenter.itemClickListener = { itemView ->
            val repository = userRepositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.repoInfo(repository))
        }
    }

    private fun loadData() {
        repositoriesRepo.getRepositories(user)
            ?.observeOn(uiScheduler)
            ?.subscribe({ repositories ->
                userRepositoriesListPresenter.repositories.clear()
                userRepositoriesListPresenter.repositories.addAll(repositories)
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
        repositoryScopeContainer.releaseUserScope()
        super.onDestroy()
    }
}