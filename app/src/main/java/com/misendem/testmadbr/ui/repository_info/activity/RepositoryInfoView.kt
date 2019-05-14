package com.misendem.testmadbr.ui.repository_info.activity

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel

@StateStrategyType(AddToEndStrategy::class)
interface RepositoryInfoView:MvpView {
    fun showInfoRepository(model: GitHubRepositoryModel)
    fun addCommit(commit: GitHubCommitModel)
    fun addAllCommit(commits: ArrayList<GitHubCommitModel>)


}