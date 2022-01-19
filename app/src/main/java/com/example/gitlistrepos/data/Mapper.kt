package com.example.gitlistrepos.data

import com.example.gitlistrepos.data.api.model.*
import com.example.gitlistrepos.domain.model.*
import java.text.SimpleDateFormat
import java.time.LocalDate


fun ReposOwnerModel.toDomain() = ReposOwnerDomainModel(
    login = login,
    avatar_url = avatar_url
)


fun ReposModel.toDomain() = ReposDomainModel(
    full_name = full_name,
    owner = owner.toDomain(),
    commits_url = commits_url.dropLast(6)
)

fun CommitsParentsModel.toDomain() = CommitsParentsDomainModel(
    sha = sha ?: ""
)

fun CommitsAuthorModel.toDomain(): CommitsAuthorDomainModel {

    val formattedDate = SimpleDateFormat("dd.MM.yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))

    return CommitsAuthorDomainModel(
    name = name ?: "",
    date = formattedDate ?: ""
    )
}

fun CommitsCommitModel.toDomain() = CommitsCommitDomaintModel(
    message = message ?: "",
    author = author.toDomain()
)

fun CommitsModel.toDomain(): CommitsDomainModel {

    val parents: List<CommitsParentsDomainModel> = parents.map { it.toDomain() }

    return CommitsDomainModel(
        parents = parents,
        commit = commit.toDomain()
    )
}


