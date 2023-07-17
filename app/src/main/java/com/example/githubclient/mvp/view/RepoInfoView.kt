package com.example.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoInfoView : MvpView {

    fun init()
    fun setTitle(text: String)
    fun setForksCount(text: String)
    fun setLanguage(text: String)
    fun setDescription(text: String)
    fun setCreatedAt(text: String)
    fun setUpdatedAt(text: String)
}