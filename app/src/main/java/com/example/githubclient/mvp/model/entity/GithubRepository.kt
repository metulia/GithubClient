package com.example.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null,
    @Expose val language: String? = null,
    @Expose val description: String? = null,
    @Expose val created_at: String? = null,
    @Expose val updated_at: String? = null
) : Parcelable