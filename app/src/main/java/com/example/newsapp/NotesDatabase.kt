package com.example.newsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Notes::class], version = 2, exportSchema = false)
@TypeConverters(TypeCaster::class)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'notes' ADD COLUMN 'date' INTEGER NOT NULL DEFAULT(0)")
            }
        }


        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "word_database"
                ).addMigrations(migration_1_2).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}