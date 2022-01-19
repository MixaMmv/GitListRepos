package com.example.gitlistrepos.domain.model

import com.google.gson.annotations.SerializedName


data class CommitsDomainModel(
    val commit: CommitsCommitDomaintModel,
    val parents: List<CommitsParentsDomainModel>
)

data class CommitsCommitDomaintModel(
    val message: String,
    val author: CommitsAuthorDomainModel,
)

data class CommitsAuthorDomainModel (
    val name: String,
    val date: String
)

data class CommitsParentsDomainModel(
    val sha: String
)

