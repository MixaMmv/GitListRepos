package com.example.gitlistrepos.features.repos_list.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitlistrepos.R
import com.example.gitlistrepos.databinding.FragmentReposListBinding
import com.example.gitlistrepos.features.commits_info.ui.CommitsInfoFragment
import com.example.gitlistrepos.features.repos_list.ui.adapter.ReposAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposListFragment: Fragment(R.layout.fragment_repos_list) {

    private val viewBinding: FragmentReposListBinding by viewBinding(FragmentReposListBinding::bind)
    private val reposListViewModel by viewModel<ReposListViewModel>()
    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter(repos = emptyList()) {
            reposListViewModel.processUiEvent(UiEvent.OnRepoClick(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.tbRepos.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.arrowLeft -> reposListViewModel.processUiEvent(UiEvent.OnLeftArrowClick)
                R.id.arrowRight -> reposListViewModel.processUiEvent(UiEvent.OnRightArrowClick)
            }
            true
        }

        viewBinding.rvRepos.adapter = reposAdapter

        val lm = (viewBinding.rvRepos.layoutManager as LinearLayoutManager)

        viewBinding.rvRepos.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                reposListViewModel.processUiEvent(UiEvent.LoadMoreRepos(lm.findLastVisibleItemPosition()))
            }
        })

        reposListViewModel.viewState.observe(viewLifecycleOwner, ::render)
        reposListViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    private fun render(viewState: ViewState) {
        reposAdapter.updateRepos(viewState.repos)

        if (viewState.errorMessage != null)
        Toast.makeText(requireContext(), "Хьюстон, " + viewState.errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenCommitsInfo -> {
                Log.d("", "CLICKED")
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, CommitsInfoFragment.newInstance(event.repo))
                    .addToBackStack("repos")
                    .commit()
            }
        }
    }


}