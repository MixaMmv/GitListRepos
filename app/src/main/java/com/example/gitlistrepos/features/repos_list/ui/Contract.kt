package com.example.gitlistrepos.features.repos_list.ui

import com.example.gitlistrepos.base.Event
import com.example.gitlistrepos.domain.model.ReposDomainModel

data class ViewState(
    val repos: List<ReposDomainModel>,
    val isLoading: Boolean,
    val errorMessage: String?,
    val lastSince: Int
)

sealed class UiEvent : Event {
    data class GetRepos(val since: Int) : UiEvent()
    data class OnRepoClick(val repo: ReposDomainModel) : UiEvent()
    object OnLeftArrowClick: UiEvent()
    object OnRightArrowClick: UiEvent()
    data class LoadMoreRepos(val index: Int): UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadRepos : DataEvent()
    data class SuccessReposRequest(val repos: List<ReposDomainModel>) : DataEvent()
    data class ErrorReposRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent : Event {
    data class OpenCommitsInfo(val repo: ReposDomainModel) : SingleEvent()
}