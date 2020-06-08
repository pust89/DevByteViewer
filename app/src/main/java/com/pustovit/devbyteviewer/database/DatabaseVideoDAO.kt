package com.pustovit.devbyteviewer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseVideoDAO {

    @Query("SELECT * FROM videos_table;")
     fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(vararg videos: DatabaseVideo)

}