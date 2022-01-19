package com.example.gitlistrepos.data.api.model

import com.google.gson.annotations.SerializedName

data class ReposModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("owner")
    val owner: ReposOwnerModel,
    @SerializedName("commits_url")
    val commits_url: String
)

data class ReposOwnerModel (
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)