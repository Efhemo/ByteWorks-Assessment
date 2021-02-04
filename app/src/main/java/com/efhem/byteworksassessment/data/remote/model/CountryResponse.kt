package com.efhem.byteworksassessment.data.remote.model


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("states")
    val states: List<StateResponse>?
)