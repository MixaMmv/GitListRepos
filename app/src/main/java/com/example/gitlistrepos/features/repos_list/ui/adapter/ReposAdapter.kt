package com.example.gitlistrepos.features.repos_list.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitlistrepos.R
import com.example.gitlistrepos.base.setThrottledClickListener
import com.example.gitlistrepos.domain.model.ReposDomainModel
import com.example.gitlistrepos.features.commits_info.ui.CommitsInfoFragment
import com.example.gitlistrepos.features.repos_list.ui.SingleEvent
import com.example.gitlistrepos.features.repos_list.ui.UiEvent

class ReposAdapter(
    private var repos: List<ReposDomainModel>,
    private val onRepoClick: (repo: ReposDomainModel) -> Unit
): RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.ivAvatar)
        val login: TextView = view.findViewById(R.id.tvLogin)
        val name: TextView = view.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(repos[position].owner.avatar_url).into(holder.avatar)
        holder.login.text = repos[position].owner.login
        holder.name.text = repos[position].full_name

        holder.itemView.setThrottledClickListener {
            onRepoClick(repos[position])
        }
    }



    fun updateRepos(newRepos: List<ReposDomainModel>) {
        repos = newRepos
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return repos.size    }

}