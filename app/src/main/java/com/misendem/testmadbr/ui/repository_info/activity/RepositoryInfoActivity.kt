package com.misendem.testmadbr.ui.repository_info.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.misendem.testmadbr.R
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.ui.repository_info.adapter.CommitsAdapter
import kotlinx.android.synthetic.main.activity_repository_info.*

class RepositoryInfoActivity : MvpAppCompatActivity(), RepositoryInfoView {

    companion object {
        private const val EXTRA_REPOSITORY = "extra_rep"

        fun start(context: Context, repositoryModel: GitHubRepositoryModel) {
            val intent = Intent(context, RepositoryInfoActivity::class.java)
            intent.putExtra(EXTRA_REPOSITORY, repositoryModel)
            context.startActivity(intent)
        }
    }

    @InjectPresenter
    lateinit var mPresenter: RepositoryInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_info)
        initUi()
    }

    private fun initUi() {

        _listCommits.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        _listCommits.adapter = CommitsAdapter()

        val model = intent.getSerializableExtra(EXTRA_REPOSITORY) as GitHubRepositoryModel
        mPresenter.loadInfo(model)
    }

    override fun showInfoRepository(model: GitHubRepositoryModel) {
        _nameRepository.text = model.nameRepository
        _descriptionRepository.text = model.description
        _starsRepository.text = "stars : ${model.countStars}"
        _languageRepository.text = "language : ${model.language}"
        _forkRepository.text = "fork : ${model.countFork}"
        _commitsRepository.text = "commits : ${model.countCommits}"
        Glide
            .with(this)
            .load(model.user.imageUrlAuthor)
            .circleCrop()
            .into(_avatarUser)
        _nameUser.text = model.user.nameAuthor
    }

    override fun addCommit(commit: GitHubCommitModel) {
        (_listCommits.adapter as CommitsAdapter).addCommit(commit)
    }

    override fun addAllCommit(commits: ArrayList<GitHubCommitModel>) {
        (_listCommits.adapter as CommitsAdapter).addAllCommit(commits)

    }
}