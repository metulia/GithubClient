package com.example.githubclient.di.repoinfo

import com.example.githubclient.App
import dagger.Module
import dagger.Provides

@Module
open class RepoInfoModule {

    @RepoInfoScope
    @Provides
    open fun scopeContainer(app: App): IRepoInfoScopeContainer = app
}