package com.example.gitlistrepos.features.commits_info.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.gitlistrepos.R
import com.example.gitlistrepos.databinding.FragmentReposInfoBinding
import com.example.gitlistrepos.domain.model.ReposDomainModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommitsInfoFragment : Fragment(R.layout.fragment_repos_info) {

    private val viewBinding: FragmentReposInfoBinding by viewBinding(FragmentReposInfoBinding::bind)

    companion object {
        private const val REPO_KEY = "repo"
        fun newInstance(repo: ReposDomainModel) = CommitsInfoFragment().apply {
            arguments = bundleOf(Pair(REPO_KEY, repo))
        }
    }

    private val repo: ReposDomainModel by lazy {
        requireArguments().getParcelable(REPO_KEY)!!
    }


    private val commitsInfoViewModel by viewModel<CommitsInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("CHECK", "OPENED")

        with(viewBinding) {
            Glide.with(requireContext()).load(repo.owner.avatar_url).into(ivAvatar)
            tvLogin.text = repo.owner.login
            tvFullName.text = repo.full_name
        }



        commitsInfoViewModel.viewState.observe(viewLifecycleOwner, ::render)

        commitsInfoViewModel.processUiEvent(UiEvent.GetCommits(repo.full_name))

    }

    private fun render(viewState: ViewState) {
        if (viewState.isLoadedSuccess) {
            with(viewBinding) {
                tvName.text = viewState.commits[0].commit.author.name ?: ""
                tvDate.text = viewState.commits[0].commit.author.date ?: ""
                tvMessage.text = viewState.commits[0].commit.message ?: ""
                tvSha.text = viewState.commits[0].parents.joinToString(separator = "\n") { it.sha } ?: ""
            }
        }

        if (viewState.errorMessage != null)
            Toast.makeText(requireContext(), "Хьюстон, " + viewState.errorMessage, Toast.LENGTH_LONG).show()

    }

}