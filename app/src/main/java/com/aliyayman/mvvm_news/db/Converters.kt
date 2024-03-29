package com.aliyayman.mvvm_news.db

import androidx.room.TypeConverter
import com.aliyayman.mvvm_news.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name,name)
    }
}