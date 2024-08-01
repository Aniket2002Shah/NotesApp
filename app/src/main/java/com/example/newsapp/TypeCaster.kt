package com.example.newsapp

import androidx.room.TypeConverter
import java.util.*

class TypeCaster {

    @TypeConverter
    fun dateToLong(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun longToDate(long:Long):Date{
        return Date(long)
    }
}