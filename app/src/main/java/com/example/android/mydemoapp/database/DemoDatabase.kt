package com.example.android.mydemoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.mydemoapp.database.current.CurrentWeatherDao
import com.example.android.mydemoapp.database.current.WeatherEntity
import com.example.android.mydemoapp.database.future.FutureEntity
import com.example.android.mydemoapp.database.future.FutureWeatherDao


@Database(
        entities = [WeatherEntity::class, FutureEntity::class],
        version = 1,
        exportSchema = false
)
abstract class DemoDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao

    companion object {

        @Volatile
        private var INSTANCE: DemoDatabase? = null

        fun getInstance(context: Context): DemoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DemoDatabase::class.java,
                            "demo_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}