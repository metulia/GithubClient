package com.example.githubclient.di

import com.example.githubclient.di.module.*
import com.example.githubclient.di.user.UserSubcomponent
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        ImageLoaderModule::class
    ]
)

interface AppComponent {
    fun userSubcomponent() : UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}