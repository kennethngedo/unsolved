package com.example.unsolved.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.unsolved.data.local.entities.StoryEntity

@Dao
interface StoryDao {
    @Query("SELECT * FROM storyentity WHERE id IS 1")
    suspend fun getStory(): StoryEntity

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg users: StoryEntity)

    @Query("DELETE FROM storyentity")
    suspend fun deleteAll()
}