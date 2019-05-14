package com.misendem.testmadbr.logic.repository

import android.arch.persistence.room.EmptyResultSetException
import com.misendem.testmadbr.App
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryJsonModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModelTable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.concurrent.thread

class Repository {

    companion object {
        private val disposables = CompositeDisposable()
        fun getAllFavoritesRepository(complete: (ArrayList<GitHubRepositoryModel>) -> Unit) {
            val flowable = App.instance.database.favoritesRepositoryDao().getAll()
            disposables.add(
                flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val arrayListGitHubRepositoryModel = arrayListOf<GitHubRepositoryModel>()
                        for (i in it) {
                            arrayListGitHubRepositoryModel.add(GitHubRepositoryModel.parceFromDb(i))
                        }
                        complete(arrayListGitHubRepositoryModel)
                    }, {
                        it.printStackTrace()
                    })
            )
        }

        fun saveInDbFavoritesRepository(model: GitHubRepositoryModel) {
            val saveModel: GitHubRepositoryModelTable = GitHubRepositoryModelTable.parce(model)

            disposables.add(
                App.instance.database.favoritesRepositoryDao().getById(model.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {
                        if (it is EmptyResultSetException)
                            thread { App.instance.database.favoritesRepositoryDao().insert(saveModel) }
                        it.printStackTrace()
                    })
            )


        }

        fun deleteFromDbFavoritesRepository(model: GitHubRepositoryModel) {
            val saveModel: GitHubRepositoryModelTable = GitHubRepositoryModelTable.parce(model)
            Observable.just(
                thread { App.instance.database.favoritesRepositoryDao().delete(saveModel) }
            )
                .subscribeOn(Schedulers.io())
                .subscribe()
        }

        fun loadPublicRepository(complete: (GitHubRepositoryModel) -> Unit) {
            disposables.add(
                App.instance.API.getPublicRepository()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ arrayGitHubRepositoryJson ->
                        //arrayGitHubRepository
                        for (i in arrayGitHubRepositoryJson)
                            loadInfoRepository(i) { repositoryModel ->
                                complete(repositoryModel)
                            }
                    }, {
                        it.printStackTrace()
                    })
            )
        }

        fun loadPublicRepositoryById(id: Int, complete: (GitHubRepositoryModel) -> Unit) {

            disposables.add(
                App.instance.API.getPublicRepositoryByPage(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ arrayGitHubRepositoryJson ->
                        //arrayGitHubRepository
                        for (i in arrayGitHubRepositoryJson)
                            loadInfoRepository(i) { repositoryModel ->
                                complete(repositoryModel)
                            }
                    }, {
                        it.printStackTrace()
                    })
            )
        }


        private fun loadInfoRepository(
            repositoryJsonModel: GitHubRepositoryJsonModel,
            complete: (GitHubRepositoryModel) -> Unit
        ) {
            disposables.add(
                App.instance.API.getInfoRepository(repositoryJsonModel.owner.nameAuthor, repositoryJsonModel.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        complete(it)
                    }, {
                        it.printStackTrace()
                    })

            )
        }

        fun loadCommitsRepository(
            repositoryModel: GitHubRepositoryModel,
            complete: (ArrayList<GitHubCommitModel>) -> Unit
        ) {
            disposables.add(
                App.instance.API.getCommitsRepository(repositoryModel.user.nameAuthor, repositoryModel.nameRepository)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        complete(it)
                    }, {
                        it.printStackTrace()
                    })


            )
        }
    }


}