package com.example.mypets

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pets")
    fun getAll(): Flow<List<Pet>>

    @Query("SELECT * FROM pets")
    fun getList(): List<Pet>

    @Query("SELECT * FROM pets WHERE id LIKE :id")
    fun findById(id: Int): Pet

    @Insert
    fun insert(pet: Pet)

    @Delete
    fun delete(pet: Pet)

    @Update
    fun update(pet: Pet)
}