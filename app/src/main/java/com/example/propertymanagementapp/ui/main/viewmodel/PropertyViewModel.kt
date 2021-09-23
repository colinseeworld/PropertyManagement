package com.example.propertymanagementapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.model.PropertyRequest
import com.example.propertymanagementapp.data.repository.PropertyRepository
import java.io.File

class PropertyViewModel : ViewModel() {
    var propertyRepository: PropertyRepository = PropertyRepository()

    var address= MutableLiveData<String>().apply { value="" }
    var city =MutableLiveData<String>().apply { value="" }
    var state =MutableLiveData<String>().apply { value="" }
    var country= MutableLiveData<String>().apply { value="" }
    var zipCode= MutableLiveData<String>().apply { value="" }
    var isMultipleUnits= MutableLiveData<Boolean>().apply { value=true}
    var purchasePrice= MutableLiveData<String>().apply { value="" }
    var isDashboard=MutableLiveData<Boolean>().apply { value=false}
    var image= MutableLiveData<String>().apply { value="" }
    var isMortgage=MutableLiveData<Boolean>().apply { value=false}
    var imageLiveData: MutableLiveData<File> =MutableLiveData()

    var toast = MutableLiveData<String>()

    fun addProperty(propertyRequest: PropertyRequest) {
        imageLiveData.value?.apply {
            propertyRepository.uploadImage(this) { isOk, data ->
               if (isOk){
                   data?.let {
                       propertyRequest.image=data.location
                       propertyRepository.addProperty(propertyRequest) { isOk ->
                           if (isOk){
                               toast.value="save success"
                           }else{
                               toast.value="save failure"
                           }
                       }
                   }
               }else{
                   toast.value="upload image failure"
               }
            }
        }

    }
}