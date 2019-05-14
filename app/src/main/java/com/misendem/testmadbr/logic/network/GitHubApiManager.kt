package com.misendem.testmadbr.logic.network

import com.misendem.testmadbr.App
import com.misendem.testmadbr.logic.model.GitHubCommitModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryJsonModel
import com.misendem.testmadbr.logic.model.GitHubRepositoryModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiManager {

//    access_token: String = "4eb3081553064c66c542576370d1a304d88e02b9"

    @GET("/repositories")
    fun getPublicRepository(
        @Query("access_token") access_token: String = App.accessTokenGitHub
    ): Flowable<ArrayList<GitHubRepositoryJsonModel>>


    @GET("/repositories")
    fun getPublicRepositoryByPage(
        @Query("since") lastId: Int,
        @Query("access_token") access_token: String = App.accessTokenGitHub
    ): Flowable<ArrayList<GitHubRepositoryJsonModel>>


    @GET("/repos/{user}/{name}")
    fun getInfoRepository(
        @Path("user") user: String,
        @Path("name") name: String,
        @Query("access_token") access_token: String = App.accessTokenGitHub
    ): Flowable<GitHubRepositoryModel>


    @GET("/repos/{user}/{name}/commits")
    fun getCommitsRepository(
        @Path("user") user: String,
        @Path("name") name: String,
        @Query("access_token") access_token: String = App.accessTokenGitHub
    ):Flowable<ArrayList<GitHubCommitModel>>


}