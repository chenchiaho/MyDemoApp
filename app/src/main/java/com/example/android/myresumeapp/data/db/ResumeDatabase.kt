package com.example.android.myresumeapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.myresumeapp.data.db.entity.Main


@Database(
        entities = [Main::class],
        version = 1
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