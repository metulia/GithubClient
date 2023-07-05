package com.example.githubclient.mvp.view.list

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}