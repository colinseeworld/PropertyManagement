package com.example.propertymanagementapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.model.LoginRequest
import com.example.propertymanagementapp.data.repository.Repository

class LoginViewModel: ViewModel() {

    fun getLoginUsers(loginRequest: LoginRequest) = Repository().userLogin(loginRequest)

}