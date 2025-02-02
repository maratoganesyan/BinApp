package com.example.binapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.binapp.data.local.dao.BinDAO
import com.example.binapp.data.local.entity.BinEntity

@Database(entities = [BinEntity::class], version = 1)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao(): BinDAO

    companion object {
        @Volatile
        private var INSTANCE: BinDatabase? = null

        fun getDatabase(context: Context): BinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BinDatabase::class.java,
                    "bin_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}