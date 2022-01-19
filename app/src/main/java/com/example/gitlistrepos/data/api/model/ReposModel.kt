package com.example.gitlistrepos.data.api.model

import com.google.gson.annotations.SerializedName

data class ReposModel (
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("owner")
    val owner: ReposOwnerModel,
    @SerializedName("commits_url")
    val commits_url: String
)