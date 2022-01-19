package com.example.gitlistrepos.data.api

import com.example.gitlistrepos.domain.model.CommitsDomainModel
import com.example.gitlistrepos.domain.model.ReposDomainModel

interface ReposRepository {
    suspend fun getRepos(since: Int): List<ReposDomainModel>
    suspend fun getCommits(owner: String, name: String): List<CommitsDomainModel>
}