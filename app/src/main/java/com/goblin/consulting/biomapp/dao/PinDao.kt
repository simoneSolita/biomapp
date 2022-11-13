package com.goblin.consulting.biomapp.dao

import androidx.room.*
import com.goblin.consulting.biomapp.model.PinItem
import java.util.*

@Dao
interface PinDao {

    @Transaction
    @Query("SELECT * FROM pin")
    fun getAllPins(): List<PinItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPin(pinItem: PinItem)

    @Query("DELETE FROM pin")
    fun deletePins()

    @Transaction
    @Query("SELECT * FROM pin WHERE insertDate >= :dateFrom AND insertDate <= :dateTo")
    fun getPinsByDate(dateFrom: Date,dateTo : Date): List<PinItem>
}