package com.misendem.testmadbr.ui.repository_info.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import kotlinx.android.synthetic.main.view_item_commit.view.*

class CommitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(commitModel: GitHubCommitModel) {
        itemView._author.text = "author : ${commitModel.commit.author.name}"
        itemView._date.text = "author : ${commitModel.commit.author.date}"
        itemView._message.text = "message : ${commitModel.commit.message}"
    }
}