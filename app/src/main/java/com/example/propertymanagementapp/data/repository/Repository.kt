package com.example.propertymanagementapp.data.repository

import com.example.propertymanagementapp.data.model.LoginRequest
import com.example.propertymanagementapp.data.model.RegisterRequest
import com.example.propertymanagementapp.data.model.TenantRequest
import com.example.propertymanagementapp.data.remotedatasource.RemoteData


class Repository {

    fun userRegister(registerRequest: RegisterRequest) =
        RemoteData().getRegisterResponse(registerRequest)

    fun userLogin(loginRequest: LoginRequest) =
        RemoteData().getLoginResponse(loginRequest)

    fun addTenant(tenantRequest: TenantRequest) =
        RemoteData().addTenantResponse(tenantRequest)
}



//class Repository @Inject constructor(var remoteData: RemoteData) {
//
//    init{
//        DaggerComponent.builder().userModule(UserModule()).build().inject(this)
//    }
//
//    fun userRegister(registerRequest: RegisterRequest) =
//        remoteData.getRegisterResponse(registerRequest)
//
//    fun userLogin(loginRequest: LoginRequest) =
//        RemoteData().getLoginResponse(loginRequest)
//
//    fun addTenant(tenantRequest: TenantRequest) =
//        remoteData.addTenantResponse(tenantRequest)
//}

