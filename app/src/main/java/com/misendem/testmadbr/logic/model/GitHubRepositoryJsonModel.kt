package com.misendem.testmadbr.logic.model

class GitHubRepositoryJsonModel(
    val id:Int,
    val full_name:String,
    val name: String,
    val owner:GitHubUserModel

) {
}


class DataJsonModel(
    val array:ArrayList<GitHubRepositoryJsonModel>
)