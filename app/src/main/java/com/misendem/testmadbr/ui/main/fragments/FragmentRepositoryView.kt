package com.misendem.testmadbr.ui.main.fragments

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface FragmentRepositoryView:MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun openInfoAboutRepository(model: GitHubRepositoryModel)

    @StateStrategyType(AddToEndStrategy::class)
    fun addRepositoryInList(it: GitHubRepositoryModel)

    fun clearListRepository()



}