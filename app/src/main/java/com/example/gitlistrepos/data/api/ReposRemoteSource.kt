package com.example.gitlistrepos.data.api

import com.example.gitlistrepos.data.api.model.CommitsModel
import com.example.gitlistrepos.data.api.model.ReposModel

class ReposRemoteSource(private val reposApi: ReposApi) {
    suspend fun getRepos(since: Int): List<ReposModel> = reposApi.getRepos(since)
    suspend fun getCommits(owner: String, name: String): List<CommitsModel> = reposApi.getCommits(owner, name)
}