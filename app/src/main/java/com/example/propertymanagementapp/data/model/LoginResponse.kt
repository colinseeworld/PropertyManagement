package com.example.propertymanagementapp.data.model

data class LoginResponse(
    val token: String?,
    val user: LoginData?,
    val error: Boolean = true,
    val message: String?
)

data class LoginData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val type: String
)