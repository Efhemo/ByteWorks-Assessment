package com.efhem.byteworksassessment.data.remote.model
import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)