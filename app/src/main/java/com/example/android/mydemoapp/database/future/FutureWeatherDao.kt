package com.example.android.mydemoapp.database.future

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFuture (vararg futureEntity: FutureEntity)

    @Query("SELECT * FROM future_table")
    fun getFutureWeatherTable(): LiveData<List<FutureEntity>>

    @Query("delete from future_table where date(date) < date(:current)")
    suspend fun deleteOldEntries(current: String)
}