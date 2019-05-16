package com.misendem.testmadbr.ui.main.fragments.all_repository

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.misendem.testmadbr.ui.main.fragments.FragmentRepositoryView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListAllRepositoryView : FragmentRepositoryView {

    fun hideRefresh()

    @StateStrategyType(SkipStrategy::class)
    fun showMessageAddInFavorite()


    fun showProgressLoading()
    fun hideProgressLoading()
}