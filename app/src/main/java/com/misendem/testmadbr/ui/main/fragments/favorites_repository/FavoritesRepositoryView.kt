package com.misendem.testmadbr.ui.main.fragments.favorites_repository

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.misendem.testmadbr.ui.main.fragments.FragmentRepositoryView

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesRepositoryView:FragmentRepositoryView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessageRemoveFromFavorite()
}