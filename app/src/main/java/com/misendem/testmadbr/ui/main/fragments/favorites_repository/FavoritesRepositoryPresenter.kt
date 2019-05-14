package com.misendem.testmadbr.ui.main.fragments.favorites_repository

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.logic.repository.Repository

@InjectViewState
class FavoritesRepositoryPresenter : MvpPresenter<FavoritesRepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadFavoritesRepository()
    }

    private fun loadFavoritesRepository() {
        Repository.getAllFavoritesRepository { arrayRepositorys ->
            viewState.clearListRepository()
            for (i in arrayRepositorys)
                viewState.addRepositoryInList(i)
        }
    }

    fun onClickRepository(it: GitHubRepositoryModel) {
        viewState.openInfoAboutRepository(it)
    }

    fun onDeleteFavoriteRepository(repositoryModel: GitHubRepositoryModel) {
        Repository.deleteFromDbFavoritesRepository(repositoryModel)
    }
}