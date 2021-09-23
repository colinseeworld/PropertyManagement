package com.example.propertymanagementapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repository.Repository
import com.example.propertymanagementapp.data.model.RegisterRequest

class RegisterViewModel: ViewModel() {

    fun getRegisteredUsers(registerRequest: RegisterRequest) = Repository().userRegister(registerRequest)
}

