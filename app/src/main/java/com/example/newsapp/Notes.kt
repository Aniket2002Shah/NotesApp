package com.example.newsapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Notes(@ColumnInfo(name = "title") val title:String,
                 @ColumnInfo(name = "date")val date: Date){
      @PrimaryKey(autoGenerate = true) var id:Int =0
}