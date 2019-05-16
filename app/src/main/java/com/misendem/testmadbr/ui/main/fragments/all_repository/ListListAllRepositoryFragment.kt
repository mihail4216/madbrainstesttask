package com.misendem.testmadbr.ui.main.fragments.all_repository

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.ui.main.fragments.adpter.RepositoryAdapter
import com.misendem.testmadbr.ui.repository_info.activity.RepositoryInfoActivity
import kotlinx.android.synthetic.main.fragment_list_repository.*
import kotlinx.android.synthetic.main.fragment_list_repository.view.*

class ListListAllRepositoryFragment : MvpAppCompatFragment(), ListAllRepositoryView {
    override fun addRepositoryInList(it: GitHubRepositoryModel) {
        (_listRepository.adapter as RepositoryAdapter).addRepository(it)
    }

    override fun clearListRepository() {
        (_listRepository.adapter as RepositoryAdapter).clearRepository()
    }

    companion object {

        fun newInstance(): ListListAllRepositoryFragment {
            val fragment = ListListAllRepositoryFragment()
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mPresenter: ListAllRepositoryPresenter

    override fun hideRefresh() {
        _refreshLayout.isRefreshing = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_repository, container, false)
        initUi(view)
        return view
    }

    override fun openInfoAboutRepository(repository: GitHubRepositoryModel) {
        RepositoryInfoActivity.start(context!!, repository)
    }

    private fun initUi(view: View) {
        view._listRepository.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RepositoryAdapter(context!!).apply {
            onClickRepository = {
                mPresenter.onClickRepository(it)
            }
            onClickBntFavorite = {
                mPresenter.onClickBntFavorite(it)
            }

            onEndListRecyclerView = {
                mPresenter.addPackRepository(it)
            }

            onAddFavoriteRepository = {
                mPresenter.onClickBntFavorite(it)
            }

            view._listRepository.adapter = this
        }

        view._refreshLayout.setOnRefreshListener {
            mPresenter.refreshListRepository()
        }
        view._refreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )


    }

    override fun showProgressLoading() {
        _progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressLoading() {
        _progressBar.visibility = View.GONE

    }

    override fun showMessageAddInFavorite() {
        Snackbar.make(_refreshLayout,"Added favorite",Snackbar.LENGTH_SHORT).show()
    }


}