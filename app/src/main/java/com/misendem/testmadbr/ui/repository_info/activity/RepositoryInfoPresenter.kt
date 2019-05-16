package com.misendem.testmadbr.ui.repository_info.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.logic.repository.Repository
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class RepositoryInfoPresenter : MvpPresenter<RepositoryInfoView>() {

    var disposable = CompositeDisposable()

    fun loadInfo(model: GitHubRepositoryModel) {
        viewState.showInfoRepository(model)
        loadCommits(model)
    }

    private fun loadCommits(model: GitHubRepositoryModel) {
        Repository.loadCommitsRepository(model){
            addCommits(it)
        }
    }

    private fun addCommits(listCommits: ArrayList<GitHubCommitModel>) {
        val list = arrayListOf<GitHubCommitModel>()
        var count = 0
        for (i in listCommits) {
            if (count == 10)
                break
            list.add(i)
            count++

        }
        viewState.setCountCommits(listCommits.size)
        viewState.addAllCommit(list)
    }


}