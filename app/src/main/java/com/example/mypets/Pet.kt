package com.example.mypets


import android.content.res.Resources
import android.content.res.Resources.getSystem
import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "age") val age:Int,
    @ColumnInfo(name = "image") val image: Bitmap,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "vaccineDate" ) val vaccineDate: String?) {

    companion object{

    }
}

