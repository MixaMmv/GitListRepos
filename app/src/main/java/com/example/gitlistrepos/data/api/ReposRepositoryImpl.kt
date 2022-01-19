package com.example.gitlistrepos.data.api

import com.example.gitlistrepos.data.toDomain
import com.example.gitlistrepos.domain.model.CommitsDomainModel
import com.example.gitlistrepos.domain.model.ReposDomainModel

class ReposRepositoryImpl(private val source: ReposRemoteSource) : ReposRepository {
    override suspend fun getRepos(since: Int): List<ReposDomainModel> =
        source.getRepos(since).map { it.toDomain() }

    override suspend fun getCommits(owner: String, name: String): List<CommitsDomainModel> =
        source.getCommits(owner, name).map { it.toDomain() }

}