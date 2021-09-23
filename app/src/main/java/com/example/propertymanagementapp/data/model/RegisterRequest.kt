package com.example.propertymanagementapp.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("type")
    var type: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)