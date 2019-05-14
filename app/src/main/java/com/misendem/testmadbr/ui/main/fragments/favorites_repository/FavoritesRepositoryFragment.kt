package com.misendem.testmadbr.ui.main.fragments.favorites_repository

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.ui.main.fragments.adpter.RepositoryAdapter
import com.misendem.testmadbr.ui.main.fragments.adpter.SwipeToDeleteCallback
import com.misendem.testmadbr.ui.repository_info.activity.RepositoryInfoActivity
import kotlinx.android.synthetic.main.fragment_list_repository.*
import kotlinx.android.synthetic.main.fragment_list_repository.view.*

class FavoritesRepositoryFragment : MvpAppCompatFragment(), FavoritesRepositoryView {

    companion object {

        fun newInstance(): FavoritesRepositoryFragment {
            val fragment = FavoritesRepositoryFragment()
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mPresenter: FavoritesRepositoryPresenter

    override fun clearListRepository() {
        (_listRepository.adapter as RepositoryAdapter).clearRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_repository, container, false)
        initUi(view)
        return view
    }

    override fun openInfoAboutRepository(model: GitHubRepositoryModel) {
        RepositoryInfoActivity.start(context!!, model)
    }

    override fun addRepositoryInList(it: GitHubRepositoryModel) {
        (_listRepository.adapter as RepositoryAdapter).addRepository(it)
    }

    private fun initUi(view: View) {
        view._listRepository.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RepositoryAdapter().apply {
            onClickRepository = {
                mPresenter.onClickRepository(it)
            }
            onDeleteFavoriteRepository = {
                mPresenter.onDeleteFavoriteRepository(it)
            }
            view._listRepository.adapter = this
            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(this))
            itemTouchHelper.attachToRecyclerView(view._listRepository)
        }
    }


}