package com.example.propertymanagementapp.data.di

import com.example.propertymanagementapp.utils.Config
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//class NetworkModule {
//
//    @Provides
//    fun retrofitProvider(): Retrofit {
//
//        return Retrofit.Builder()
//            .baseUrl(Config.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//    }
//
//}