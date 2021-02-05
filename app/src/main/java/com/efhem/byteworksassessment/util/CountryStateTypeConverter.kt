package com.efhem.byteworksassessment.util

import androidx.room.TypeConverter
import com.efhem.byteworksassessment.data.CountryState
import com.efhem.byteworksassessment.data.remote.model.StateResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class CountryStateTypeConverter : Serializable {
    @TypeConverter
    fun fromCountryState(users: List<StateResponse?>?): String? {
        if (users == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<StateResponse?>?>() {}.type
        return gson.toJson(users, type)
    }

    @TypeConverter
    fun toCountryState(usersString: String?): List<StateResponse>? {
        if (usersString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<StateResponse?>?>() {}.type
        return gson.fromJson<List<StateResponse>>(usersString, type)
    }
}