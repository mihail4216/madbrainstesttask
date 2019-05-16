package com.misendem.testmadbr.ui.main.fragments.adpter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.misendem.testmadbr.R


class SwipeToFavoriteCallback(adapter: RepositoryAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {

    init {

    }


    private var mAdapter = adapter

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
        val position = p0.adapterPosition
        mAdapter.addFavorite(position)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 0

        val iconMargin = (itemView.height - icon!!.getIntrinsicHeight()) / 2
        val iconTop = itemView.top + (itemView.height - icon!!.getIntrinsicHeight()) / 2
        val iconBottom = iconTop + icon!!.getIntrinsicHeight()

        when {
            dX > 0 -> { // Swiping to the right
                val iconLeft = itemView.left - iconMargin - icon!!.getIntrinsicWidth()
                val iconRight = itemView.left + iconMargin

                icon = ContextCompat.getDrawable(
                    mAdapter.context,
                    R.drawable.ic_favorite_border_black_24dp
                )
                background = ColorDrawable(Color.GREEN)

                icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
//                icon!!.setBounds(100, 100, 100, 100)

                background.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.left + dX.toInt() + backgroundCornerOffset,
                    itemView.bottom
                )

            }
            dX < 0 -> { // Swiping to the left
                val iconLeft = itemView.right - iconMargin - icon!!.getIntrinsicWidth()
                val iconRight = itemView.right - iconMargin

                icon = ContextCompat.getDrawable(
                    mAdapter.context,
                    R.drawable.ic_delete_black_24dp
                )
                background =  ColorDrawable(Color.RED)
                icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
//                icon!!.setBounds(100, 100, 100, 100)



                background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
            }
            else -> // view is unSwiped
                background.setBounds(0, 0, 0, 0)
        }

        background.draw(c)
        icon?.draw(c)

    }

    private var icon: Drawable? = ContextCompat.getDrawable(
        mAdapter.context,
        R.drawable.ic_favorite_border_black_24dp
    )
    private var background: ColorDrawable = ColorDrawable(Color.RED)


}