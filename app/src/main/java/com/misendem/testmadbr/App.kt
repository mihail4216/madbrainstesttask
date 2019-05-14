package com.misendem.testmadbr

import android.app.Application
import android.arch.persistence.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.misendem.testmadbr.logic.database.Database
import com.misendem.testmadbr.logic.network.GitHubApiManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    private object Holder {
        lateinit var instance: App
    }

    companion object {
        private const val LOG_TAG = "App"
        val instance: App by lazy { Holder.instance }
        const val accessTokenGitHub: String = "4eb3081553064c66c542576370d1a304d88e02b9"
    }

    init {
        Holder.instance = this
    }

    lateinit var API: GitHubApiManager
    lateinit var database: Database

    override fun onCreate() {
        super.onCreate()
        initNetwork()
        initDb()

    }

    private fun initDb() {
        database = Room.databaseBuilder(applicationContext, Database::class.java, "database").build()
    }

    private fun initNetwork() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val static = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        API = static.create(GitHubApiManager::class.java)
    }


}