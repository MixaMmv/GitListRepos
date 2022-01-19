package com.example.gitlistrepos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ReposDomainModel(
    val id: Int,
    val full_name: String,
    val owner: ReposOwnerDomainModel,
    val commits_url: String
) : Parcelable

@Parcelize
data class ReposOwnerDomainModel(
    val login: String,
    val avatar_url: String
) : Parcelable

