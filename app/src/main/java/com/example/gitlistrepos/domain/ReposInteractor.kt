package com.example.gitlistrepos.domain

import com.example.gitlistrepos.base.attempt
import com.example.gitlistrepos.data.api.ReposRepository

class ReposInteractor(private val reposRepository: ReposRepository) {
    suspend fun getRepos(since: Int) = attempt {
        reposRepository.getRepos(since)
    }
    suspend fun getCommits(owner: String, name: String) = attempt {
        reposRepository.getCommits(owner, name)
    }
}