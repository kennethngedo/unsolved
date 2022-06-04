package com.example.unsolved.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.unsolved.common.utils.JsonParser
import com.example.unsolved.domain.model.Character
import com.example.unsolved.domain.model.Media
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromCharactersJson(json: String): List<Character> {
        return jsonParser.fromJson<List<Character>>(
            json,
            object : TypeToken<ArrayList<Character>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCharactersJson(characters: List<Character>): String {
        return jsonParser.toJson(
            characters,
            object : TypeToken<ArrayList<Character>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromMediaJson(json: String): List<Media> {
        return jsonParser.fromJson<List<Media>>(
            json,
            object : TypeToken<ArrayList<Media>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMediaJson(characters: List<Media>): String {
        return jsonParser.toJson(
            characters,
            object : TypeToken<ArrayList<Media>>(){}.type
        ) ?: "[]"
    }
}