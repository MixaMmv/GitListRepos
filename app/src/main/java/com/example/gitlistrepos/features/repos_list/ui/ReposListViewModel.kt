package com.example.gitlistrepos.features.repos_list.ui

import android.util.Log
import com.example.gitlistrepos.base.BaseViewModel
import com.example.gitlistrepos.base.Event
import com.example.gitlistrepos.base.SingleLiveEvent
import com.example.gitlistrepos.domain.ReposInteractor

class ReposListViewModel(
    private val reposInteractor: ReposInteractor
) : BaseViewModel<ViewState>() {

    val singleLiveEvent = SingleLiveEvent<SingleEvent>()

    init {
        processUiEvent(UiEvent.GetRepos(0))
    }

    override fun initialViewState(): ViewState =
        ViewState(
            repos = emptyList(),
            isLoading = true,
            errorMessage = null,
            since = 0
        )


    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.GetRepos -> {
                reposInteractor.getRepos(event.since).fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorReposRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessReposRequest(it))
                    }
                )
            }

            is UiEvent.OnRepoClick -> {
                singleLiveEvent.value = SingleEvent.OpenCommitsInfo(event.repo)
                Log.d("CHECK", event.repo.toString())
            }

            is DataEvent.SuccessReposRequest -> {
                return previousState.copy(
                    repos = event.repos,
                    isLoading = false,
                    errorMessage = null
                )
            }

            is DataEvent.ErrorReposRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }

            is UiEvent.OnLeftArrowClick -> {
                if (previousState.since != 0) {
                    val since = previousState.since - 100
                    processUiEvent(UiEvent.GetRepos(since))
                    return previousState.copy(since = since)
                }
            }

            is UiEvent.OnRightArrowClick -> {
                val since = previousState.since + 100
                processUiEvent(UiEvent.GetRepos(since))
                return previousState.copy(since = since)
            }


        }
        return null
    }

}