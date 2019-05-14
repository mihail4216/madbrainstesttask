package com.misendem.testmadbr.ui.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.misendem.testmadbr.R
import com.misendem.testmadbr.ui.main.activity.adapter.ViewPagerAdapter
import com.misendem.testmadbr.ui.main.fragments.all_repository.ListListAllRepositoryFragment
import com.misendem.testmadbr.ui.main.fragments.favorites_repository.FavoritesRepositoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView {


    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()


    }

    private fun initUi() {
        ViewPagerAdapter(supportFragmentManager).also {
            val listFragment = arrayListOf<Fragment>(
                ListListAllRepositoryFragment.newInstance(),
                FavoritesRepositoryFragment.newInstance()
            )
            it.arrayListFragment.addAll(listFragment)
            _viewPager.adapter = it
        }
        _tabLayout.setupWithViewPager(_viewPager)
    }

}
