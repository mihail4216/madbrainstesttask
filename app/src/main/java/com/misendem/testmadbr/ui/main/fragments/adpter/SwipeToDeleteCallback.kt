package com.misendem.testmadbr.ui.main.fragments.adpter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SwipeToDeleteCallback(adapter: RepositoryAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {


    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return true
    }

    private var mAdapter = adapter


    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
        val position = p0.adapterPosition
        mAdapter.deleteItem(position)
    }
}