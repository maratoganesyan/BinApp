package com.example.binapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binapp.data.local.entity.BinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binEntity: BinEntity)

    @Query("SELECT * FROM bin")
    fun getAll(): Flow<List<BinEntity>>

    @Query("DELETE FROM bin")
    suspend fun deleteAll()
}