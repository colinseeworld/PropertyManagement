package com.example.propertymanagementapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)