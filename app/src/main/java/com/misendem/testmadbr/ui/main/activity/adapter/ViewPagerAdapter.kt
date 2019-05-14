package com.misendem.testmadbr.ui.main.activity.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.misendem.testmadbr.App
import com.misendem.testmadbr.R

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val arrayListFragment = arrayListOf<Fragment>()
    override fun getItem(p0: Int): Fragment {
        return arrayListFragment[p0]
    }

    override fun getCount(): Int {
        return arrayListFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> App.instance.getString(R.string.label_all_repository)
            1 -> App.instance.getString(R.string.label_favorites_repository)
            else -> "nothing"

        }
    }
}