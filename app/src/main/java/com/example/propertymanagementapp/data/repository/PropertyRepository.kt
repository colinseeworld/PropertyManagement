package com.example.propertymanagementapp.data.repository

import com.example.propertymanagementapp.data.api.ApiClient
import com.example.propertymanagementapp.data.model.PropertyRequest
import com.example.propertymanagementapp.data.model.UploadImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PropertyRepository {
    fun uploadImage(file: File, onComplete: (isOk: Boolean, data: UploadImageResponse?) -> Unit) {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                val part: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
                val response = ApiClient.getUserApiService().uploadImage(part)
                if (response.error) {
                    withContext(Dispatchers.Main) {
                        onComplete(false, null)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onComplete(true, response.data)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onComplete(false, null)
                }
            }
        }
    }

    fun  addProperty(request: PropertyRequest, onComplete: (isOk: Boolean)-> Unit){
        GlobalScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Default) {
                try {
                    val response = ApiClient.getUserApiService().addProperty(request)
                    if (response.error) {
                        withContext(Dispatchers.Main) {
                            onComplete(false)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onComplete(true)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        onComplete(false)
                    }
                }
            }
        }

    }
}

