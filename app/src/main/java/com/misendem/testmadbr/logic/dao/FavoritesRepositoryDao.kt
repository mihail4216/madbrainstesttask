package com.misendem.testmadbr.logic.dao

import android.arch.persistence.room.*
import com.misendem.testmadbr.logic.model.GitHubRepositoryModelTable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FavoritesRepositoryDao {

    @Query("SELECT * FROM githubrepositorymodeltable")
    fun getAll(): Flowable<List<GitHubRepositoryModelTable>>

    @Query("SELECT * FROM githubrepositorymodeltable WHERE id = :id")
    fun getById(id: Int): Single<GitHubRepositoryModelTable>

    @Insert
    fun insert(model: GitHubRepositoryModelTable)

    @Delete
    fun delete(model: GitHubRepositoryModelTable)

    @Update
    fun update(model: GitHubRepositoryModelTable)
}