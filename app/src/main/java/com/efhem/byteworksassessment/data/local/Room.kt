package com.efhem.byteworksassessment.data.local

import CountryStateDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [], version = 1, exportSchema = false)
abstract class ByteWorksDatabase : RoomDatabase() {
    abstract fun daoCountries(): CountryStateDao
}

private lateinit var INSTANCE: ByteWorksDatabase

fun database(context: Context): ByteWorksDatabase {
    synchronized(ByteWorksDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context,
                ByteWorksDatabase::class.java, "ByteWorksDatabase"
            ).build()
        }
    }
    return INSTANCE
}