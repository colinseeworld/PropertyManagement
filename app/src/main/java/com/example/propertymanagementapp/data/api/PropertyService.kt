package com.example.propertymanagementapp.data.api

import com.example.propertymanagementapp.data.model.*
import com.example.propertymanagementapp.utils.Config
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PropertyService {

    @POST("${Config.END_POINT_REGISTER}")
    fun registerUsers(@Body registerRequest: RegisterRequest): Single<RegisterResponse>

    @POST("${Config.END_POINT_LOGIN}")
    fun loginUsers(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @POST("${Config.END_POINT_TENANT}")
    fun addTenant(@Body tenantRequest: TenantRequest): Single<TenantResponse>

    @POST("${Config.END_POINT_PROPERTY}")
    suspend fun addProperty(@Body request: PropertyRequest): BaseResponse<PropertyRequest>

    @Multipart
    @POST("upload/property/picture")
    suspend fun uploadImage( @Part file: MultipartBody.Part): BaseResponse<UploadImageResponse>

}




