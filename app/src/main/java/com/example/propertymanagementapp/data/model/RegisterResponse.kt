package com.example.propertymanagementapp.data.model

data class RegisterResponse(
    val `data`: RegisterData?,
    val error: Boolean = true,
    val message: String
)

data class RegisterData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val landlordEmail: String,
    val name: String,
    val password: String,
    val type: String
)