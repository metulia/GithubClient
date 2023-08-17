package com.example.githubclient.di.module

import android.widget.ImageView
import com.example.githubclient.mvp.view.list.IImageLoader
import com.example.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun loadImage(): IImageLoader<ImageView> = GlideImageLoader()
}