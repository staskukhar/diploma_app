package com.example.mypets

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Pet::class],
    version = 1,)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): PetDao
    companion object{
        fun getDb(context: Context) : AppDatabase{
            return Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            "main4.db").build()
        }
    }
}