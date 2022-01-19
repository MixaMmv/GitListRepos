package com.example.gitlistrepos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReposOwnerDomainModel(val login: String, val avatar_url: String): Parcelable
