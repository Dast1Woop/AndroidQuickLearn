package com.example.myfirstapplication


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("status")
    val status: String?
)