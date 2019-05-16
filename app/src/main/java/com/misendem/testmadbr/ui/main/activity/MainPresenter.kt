package com.misendem.testmadbr.ui.main.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.misendem.testmadbr.logic.repository.Repository

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    override fun onDestroy() {
        super.onDestroy()
        Repository.dispose()
    }
}