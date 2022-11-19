package com.goblin.consulting.biomapp.di

import android.content.Context
import com.goblin.consulting.biomapp.dao.PinDao
import com.goblin.consulting.biomapp.db.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BioMappModule {

    private val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd")
        .registerTypeAdapter(LocalDate::class.java, DateTypeAdapter().nullSafe())
        .create()

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDbInstance(context)
    }

    @Provides
    @Singleton
    fun getPinDao(appDatabase: AppDatabase): PinDao {
        return appDatabase.getPinDao()
    }
}