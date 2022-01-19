package com.example.gitlistrepos.data.api.model

import com.google.gson.annotations.SerializedName

data class CommitsModel(
    @SerializedName("commit")
    val commit: CommitsCommitModel,
    @SerializedName("parents")
    val parents: List<CommitsParentsModel>
)

data class CommitsCommitModel(
    @SerializedName("message")
    val message: String?,
    @SerializedName("author")
    val author: CommitsAuthorModel,
)

data class CommitsAuthorModel (
    @SerializedName("name")
    val name: String?,
    @SerializedName("date")
    val date: String?
)

data class CommitsParentsModel(
    @SerializedName("sha")
    val sha: String?
)


