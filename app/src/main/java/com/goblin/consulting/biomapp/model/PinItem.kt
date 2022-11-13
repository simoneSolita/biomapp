package com.goblin.consulting.biomapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "pin")
data class PinItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPin")
    val id: Int = 0,

    @ColumnInfo(name = "insertDate")
    val insertDate: Date,

    @ColumnInfo(name = "modifiedDate")
    val modifiedDate: Date,

    @ColumnInfo(name = "latitude")
    val latitude: String,

    @ColumnInfo(name = "longitude")
    val longitude: String,

    @ColumnInfo(name = "height")
    val height: String,

    @ColumnInfo(name = "precision")
    val precision: String,

    @ColumnInfo(name = "notes")
    val notes: String
)