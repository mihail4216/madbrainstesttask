package com.misendem.testmadbr.logic.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class GitHubRepositoryModelTable(

    @PrimaryKey
    val id: Int,
    val userId: Int,
    val userName: String,
    val userPhoto: String,
    val fullName: String,
    val nameRepository: String,
    val description: String,
    val language: String,
    val countFork: Int,
    val countStars: Int,
    val countCommits: Int = 0

//    val arrayListCommits: ArrayList<GitHubCommitModel>?
) : Serializable {

    companion object {

        fun parce(model: GitHubRepositoryModel): GitHubRepositoryModelTable {
            return GitHubRepositoryModelTable(
                model.id,
                model.user.id,
                model.user.nameAuthor,
                model.user.imageUrlAuthor,
                model.fullName,
                model.nameRepository,
                model.description,
                model.language,
                model.countFork,
                model.countStars,
                model.countCommits
            )
        }

    }
}