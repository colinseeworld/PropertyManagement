package com.example.propertymanagementapp.data.di

import com.example.propertymanagementapp.data.remotedatasource.RemoteData
import com.example.propertymanagementapp.utils.Config
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//class UserModule {
//
//    @Provides
//    fun RegisterProvider(): String{
//        return "hello"
//    }
//
//    @Provides
//    fun providesRemoteData(): RemoteData{
//        return RemoteData()
//    }
//}