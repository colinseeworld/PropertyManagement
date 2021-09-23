package com.example.propertymanagementapp.data.model

import com.google.gson.annotations.SerializedName

data class TenantRequest(
    @SerializedName("propertyId")
    var propertyId: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String
)