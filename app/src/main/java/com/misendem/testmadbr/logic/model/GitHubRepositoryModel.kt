package com.misendem.testmadbr.logic.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class GitHubRepositoryModel(

    @PrimaryKey
    val id: Int,
    @SerializedName("owner")
    val user:GitHubUserModel,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val nameRepository: String,
    val description: String,
    val language: String,
    @SerializedName("forks")
    val countFork: Int,
    @SerializedName("stargazers_count")
    val countStars: Int,
    val countCommits: Int=0

//    val arrayListCommits: ArrayList<GitHubCommitModel>?
):Serializable{
    companion object {
        fun parceFromDb(model:GitHubRepositoryModelTable):GitHubRepositoryModel{
            return GitHubRepositoryModel(
                model.id,
                GitHubUserModel(model.userId,model.userName,model.userPhoto),
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