package com.example.propertymanagementapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.model.TenantRequest
import com.example.propertymanagementapp.data.repository.Repository

class AddTenantViewModel: ViewModel() {

    fun addTenants(tenantRequest: TenantRequest) = Repository().addTenant(tenantRequest)

}