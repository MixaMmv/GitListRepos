package com.example.gitlistrepos.features.commits_info.ui

import com.example.gitlistrepos.base.Event
import com.example.gitlistrepos.domain.model.CommitsDomainModel

data class ViewState(
    val commits: List<CommitsDomainModel>,
    val isLoadedSuccess: Boolean,
    val errorMessage: String?
)

sealed class UiEvent : Event {
    data class GetCommits(val fullName: String) : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadCommits : DataEvent()
    data class SuccessCommitsRequest(val commits: List<CommitsDomainModel>) : DataEvent()
    data class ErrorCommitsRequest(val errorMessage: String) : DataEvent()
}