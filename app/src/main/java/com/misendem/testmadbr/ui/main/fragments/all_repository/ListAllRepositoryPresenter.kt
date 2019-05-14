package com.misendem.testmadbr.ui.main.fragments.all_repository

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.misendem.testmadbr.App
import com.misendem.testmadbr.logic.model.GitHubRepositoryJsonModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.logic.repository.Repository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ListAllRepositoryPresenter : MvpPresenter<ListAllRepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAllPublicRepository()
    }

    fun loadAllPublicRepository() {
        Repository.loadPublicRepository {
            viewState.addRepositoryInList(it)
        }
    }

    fun onClickRepository(it: GitHubRepositoryModel) {
        viewState.openInfoAboutRepository(it)
    }

    fun onClickBntFavorite(repositoryModel: GitHubRepositoryModel) {
        Repository.saveInDbFavoritesRepository(repositoryModel)
    }

    fun addPackRepository(repository: GitHubRepositoryModel) {
        Repository.loadPublicRepositoryById(repository.id){
            viewState.addRepositoryInList(it)
        }
    }

    fun refreshListRepository() {
        viewState.clearListRepository()
        Repository.loadPublicRepository {
            viewState.hideRefresh()
            viewState.addRepositoryInList(it)
        }
    }
}