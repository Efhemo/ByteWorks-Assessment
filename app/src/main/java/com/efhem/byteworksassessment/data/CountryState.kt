package com.efhem.byteworksassessment.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efhem.byteworksassessment.data.remote.model.StateResponse
import com.google.gson.annotations.SerializedName


@Entity
data class CountryState(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    @PrimaryKey
    val name: String,
    @SerializedName("states")
    val states: List<StateResponse>?
)