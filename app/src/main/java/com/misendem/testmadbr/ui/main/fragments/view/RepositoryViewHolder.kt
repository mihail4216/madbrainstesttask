package com.misendem.testmadbr.ui.main.fragments.view

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import kotlinx.android.synthetic.main.view_item_repository.view.*

class RepositoryViewHolder(
    itemView: View,
    var onClickBntFavorite: (GitHubRepositoryModel) -> Unit,
    var onDeleteFavoriteRepository: (GitHubRepositoryModel) -> Unit,
    var onClickRepository: (GitHubRepositoryModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun onBind(repositoryModel: GitHubRepositoryModel) {
        itemView._nameRepository.text = repositoryModel.nameRepository
        itemView._descriptionRepository.text = repositoryModel.description
        itemView._starsRepository.text = "${itemView.context.getString(R.string.stars)} : ${repositoryModel.countStars}"
        itemView._languageRepository.text =
                "${itemView.context.getString(R.string.language)} : ${repositoryModel.language}"

        itemView._forkRepository.text = "${itemView.context.getString(R.string.fork)} : ${repositoryModel.countFork}"
        itemView._commitsRepository.text =
                "${itemView.context.getString(R.string.commits)} : ${repositoryModel.countCommits}"

        itemView.setOnClickListener {
            onDeleteFavoriteRepository(repositoryModel)
        }
        itemView._btnFavorite.setOnClickListener {
            onClickBntFavorite(repositoryModel)
        }

        itemView._btnDelete.setOnClickListener {
            onDeleteFavoriteRepository(repositoryModel)
        }

        itemView._container.setOnClickListener { onClickRepository(repositoryModel) }
    }
}