package com.efhem.byteworksassessment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.efhem.byteworksassessment.data.CountryState
import com.efhem.byteworksassessment.data.local.dao.CountryStateDao
import com.efhem.byteworksassessment.util.CountryStateTypeConverter


@Database(entities = [CountryState::class], version = 1, exportSchema = false)
@TypeConverters(CountryStateTypeConverter::class )
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