package com.example.unsolved.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.unsolved.data.local.entities.StoryEntity

@Database(
    entities = [StoryEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class StoryDatabase : RoomDatabase() {
    abstract val dao: StoryDao
}