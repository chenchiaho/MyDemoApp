package com.example.android.myresumeapp.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
        entities = [WeatherResponse::class],
        version = 1,
        exportSchema = false
)
abstract class ResumeDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {

        @Volatile
        private var INSTANCE: ResumeDatabase? = null

        fun getInstance(context: Context): ResumeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ResumeDatabase::class.java,
                            "resume_database"
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