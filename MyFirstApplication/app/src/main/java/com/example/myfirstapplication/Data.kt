package com.example.myfirstapplication


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("endTime")
    val endTime: String?,
    @SerializedName("id")
    val id: String?
)