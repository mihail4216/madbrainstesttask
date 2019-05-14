package com.misendem.testmadbr.ui.main.fragments.adpter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.ui.main.fragments.view.RepositoryViewHolder

class RepositoryAdapter : RecyclerView.Adapter<RepositoryViewHolder>() {

    val arrayListRepository = arrayListOf<GitHubRepositoryModel>()
    var onClickRepository: (GitHubRepositoryModel) -> Unit = {}
    var onClickBntFavorite: (GitHubRepositoryModel) -> Unit = {}
    var onDeleteFavoriteRepository: (GitHubRepositoryModel) -> Unit = {}
    var onEndListRecyclerView: (GitHubRepositoryModel) -> Unit = {}


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_item_repository, p0, false)
        return RepositoryViewHolder(view, onClickBntFavorite)
    }

    override fun getItemCount(): Int {
        return arrayListRepository.size
    }

    override fun onBindViewHolder(p0: RepositoryViewHolder, p1: Int) {
        p0.onBind(arrayListRepository[p1])
        p0.itemView.setOnClickListener { onClickRepository(arrayListRepository[p1]) }
        if (p1 == arrayListRepository.size - 1)
            onEndListRecyclerView(arrayListRepository[p1])
    }

    fun addRepository(repositoryModel: GitHubRepositoryModel) {
        arrayListRepository.add(repositoryModel)
        notifyItemChanged(arrayListRepository.size - 1)
    }

    fun clearRepository() {
        val size = arrayListRepository.size
        arrayListRepository.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun deleteItem(position: Int) {
        onDeleteFavoriteRepository(arrayListRepository[position])
        arrayListRepository.removeAt(position)
        notifyItemRemoved(position)
    }
}