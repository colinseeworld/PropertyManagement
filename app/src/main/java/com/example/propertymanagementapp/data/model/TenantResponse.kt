package com.example.propertymanagementapp.data.model

data class TenantResponse(
//    val count: Int,
    val `data`: TenantData?,
    val error: Boolean
)

data class TenantData(
    val __v: Int,
    val _id: String,
    val email: String,
    val name: String,
    val propertyId: String
)


