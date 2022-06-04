package com.example.unsolved.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unsolved.data.local.entities.CharacterEntity
import com.example.unsolved.data.local.entities.MediaEntity
import com.example.unsolved.data.local.entities.StoryEntity

@Database(
    entities = [StoryEntity::class, CharacterEntity::class, MediaEntity::class],
    version = 1
)
abstract class StoryDatabase : RoomDatabase() {
    abstract val dao: StoryDao
}