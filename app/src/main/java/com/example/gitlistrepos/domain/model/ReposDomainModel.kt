package com.example.gitlistrepos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ReposDomainModel(
    val full_name: String,
    val owner: ReposOwnerDomainModel,
    val commits_url: String
): Parcelable



