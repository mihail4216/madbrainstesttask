package com.misendem.testmadbr.ui.main.fragments.adpter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.ui.main.fragments.view.RepositoryViewHolder

class RepositoryPagedAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<GitHubRepositoryModel>
    ) : PagedListAdapter<GitHubRepositoryModel, RepositoryViewHolder>(diffUtilCallback) {

    var onClickRepository: (GitHubRepositoryModel) -> Unit = {}
    var onClickBtnFavorite: (GitHubRepositoryModel) -> Unit = {}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_item_repository, p0, false)
        return RepositoryViewHolder(view, onClickBtnFavorite)
    }

    override fun onBindViewHolder(p0: RepositoryViewHolder, p1: Int) {
        p0.onBind(getItem(p1)!!)
        p0.itemView.setOnClickListener { onClickRepository(getItem(p1)!!) }
    }
}