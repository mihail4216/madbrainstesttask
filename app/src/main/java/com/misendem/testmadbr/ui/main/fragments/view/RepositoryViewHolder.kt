package com.misendem.testmadbr.ui.main.fragments.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import kotlinx.android.synthetic.main.view_item_repository.view.*

class RepositoryViewHolder(
    itemView: View,
    var onClickBntFavorite: (GitHubRepositoryModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun onBind(repositoryModel: GitHubRepositoryModel) {
        itemView._nameRepository.text = repositoryModel.nameRepository
        itemView._descriptionRepository.text = repositoryModel.description
        itemView._starsRepository.text = "stars : ${repositoryModel.countStars}"
        itemView._languageRepository.text = "language : ${repositoryModel.language}"
        itemView._forkRepository.text = "fork : ${repositoryModel.countFork}"
        itemView._commitsRepository.text = "commits : ${repositoryModel.countCommits}"
        itemView._btnAddFavorites.setOnClickListener {
            onClickBntFavorite(repositoryModel)
        }
    }
}