package com.example.gitlistrepos.data.api.model

import com.google.gson.annotations.SerializedName

data class ReposOwnerModel (
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
    )