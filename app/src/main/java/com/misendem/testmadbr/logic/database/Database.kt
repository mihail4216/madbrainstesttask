package com.misendem.testmadbr.logic.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.misendem.testmadbr.logic.dao.FavoritesRepositoryDao
import com.misendem.testmadbr.logic.model.GitHubRepositoryModelTable

@Database(entities = [GitHubRepositoryModelTable::class], version = 2,exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun favoritesRepositoryDao(): FavoritesRepositoryDao

}