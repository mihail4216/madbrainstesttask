package com.misendem.testmadbr.ui.repository_info.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.ui.repository_info.view.CommitViewHolder

class CommitsAdapter : RecyclerView.Adapter<CommitViewHolder>() {

    val arrayListRepository = arrayListOf<GitHubCommitModel>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommitViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_item_commit, p0, false)
        return CommitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayListRepository.size
    }

    override fun onBindViewHolder(p0: CommitViewHolder, p1: Int) {
        p0.onBind(arrayListRepository[p1])
    }

    fun addCommit(commitModel: GitHubCommitModel) {
        arrayListRepository.add(commitModel)
        notifyItemChanged(arrayListRepository.size - 1)
    }

    fun addAllCommit(commits: ArrayList<GitHubCommitModel>) {
        arrayListRepository.addAll(commits)
        notifyDataSetChanged()
    }
}