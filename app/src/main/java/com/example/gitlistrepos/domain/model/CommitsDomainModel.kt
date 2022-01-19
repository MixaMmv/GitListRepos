package com.example.gitlistrepos.domain.model



data class CommitsDomainModel(
    val commit: CommitsCommitDomainModel,
    val parents: List<CommitsParentsDomainModel>
)

data class CommitsCommitDomainModel(
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

