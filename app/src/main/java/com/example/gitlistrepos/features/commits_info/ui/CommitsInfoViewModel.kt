package com.example.gitlistrepos.features.commits_info.ui

import android.util.Log
import com.example.gitlistrepos.base.BaseViewModel
import com.example.gitlistrepos.base.Event
import com.example.gitlistrepos.domain.ReposInteractor


class CommitsInfoViewModel(private val reposInteractor: ReposInteractor) : BaseViewModel<ViewState>() {

    init {
    }

    override fun initialViewState(): ViewState = ViewState(
        commits = emptyList(),
        isLoadedSuccess = false,
        errorMessage = null
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.GetCommits -> {
                reposInteractor.getCommits(event.fullName.substringBefore("/"), event.fullName.substringAfter("/")
                ).fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorCommitsRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessCommitsRequest(it))
                    }
                )
            }

            is DataEvent.SuccessCommitsRequest -> {
                return previousState.copy(
                    commits = event.commits,
                    isLoadedSuccess = true,
                    errorMessage = null
                )
            }

            is DataEvent.ErrorCommitsRequest -> {
                return previousState.copy(
                    isLoadedSuccess = false,
                    errorMessage = event.errorMessage
                )
            }

        }
        return null
    }

}