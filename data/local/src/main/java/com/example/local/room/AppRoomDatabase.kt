package com.example.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.APP_DATABASE_NAME
import com.example.local.room.common.MoviesDAO
import com.example.local.room.common.MoviesEntity
import com.example.local.room.converters.*

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    MoviesConverter::class,
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun moviesDAO(): MoviesDAO

    companion object {
        @Volatile
        private var instance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase =
            instance ?: synchronized(this) {
                buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppRoomDatabase::class.java, APP_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}