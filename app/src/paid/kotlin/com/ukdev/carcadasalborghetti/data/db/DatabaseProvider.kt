package com.ukdev.carcadasalborghetti.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ukdev.carcadasalborghetti.domain.model.Media

@Database(entities = [Media::class], version = 1, exportSchema = false)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun getFavouritesDao(): FavouritesDao
}