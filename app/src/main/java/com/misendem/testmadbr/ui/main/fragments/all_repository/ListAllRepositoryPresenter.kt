package com.misendem.testmadbr.ui.main.fragments.all_repository

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.logic.repository.Repository

@InjectViewState
class ListAllRepositoryPresenter : MvpPresenter<ListAllRepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgressLoading()
        loadAllPublicRepository()
    }

    private fun loadAllPublicRepository() {
        //        загружаю публичные репосзитории
        Repository.loadPublicRepository { modelGitHubRepository ->
            //            дальше почему-то в джсон не приходит кол-во коммитов приходится запрашивать список коммитоB
            loadCountCommits(modelGitHubRepository) {

                viewState.hideProgressLoading()
            }
        }
    }

    fun onClickRepository(it: GitHubRepositoryModel) {
        viewState.openInfoAboutRepository(it)
    }

    fun onClickBntFavorite(repositoryModel: GitHubRepositoryModel) {
        Repository.saveInDbFavoritesRepository(repositoryModel)
        viewState.showMessageAddInFavorite()
    }

    fun addPackRepository(repository: GitHubRepositoryModel) {
        Repository.loadPublicRepositoryById() { modelGitHubRepository ->
            loadCountCommits(modelGitHubRepository) {}
        }
    }

    fun refreshListRepository() {
        viewState.clearListRepository()
        Repository.loadPublicRepository { modelGitHubRepository ->
            loadCountCommits(modelGitHubRepository) {
                viewState.hideRefresh()
            }
        }
    }

    fun loadCountCommits(
        modelGitHubRepository: GitHubRepositoryModel,
        onSuccess: () -> Unit
    ) {
        Repository.loadCommitsRepository(modelGitHubRepository) {
            onSuccess()
            modelGitHubRepository.countCommits = it.size
            viewState.addRepositoryInList(modelGitHubRepository)
        }
    }
}