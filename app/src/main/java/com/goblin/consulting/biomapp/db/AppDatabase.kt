package com.goblin.consulting.biomapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.goblin.consulting.biomapp.dao.PinDao
import com.goblin.consulting.biomapp.model.PinItem

@Database(entities = [PinItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPinDao(): PinDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDbInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "BIOMAPP_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}