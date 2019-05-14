package com.misendem.testmadbr.logic.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class GitHubUserModel(
    @PrimaryKey
    val id:Int,
    @SerializedName("login")
    val nameAuthor: String,
    @SerializedName("avatar_url")
    val imageUrlAuthor: String
):Serializable