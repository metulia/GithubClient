package com.example.githubclient.mvp.presenter.list

import com.example.githubclient.mvp.view.list.ItemView

interface IListPresenter <V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}