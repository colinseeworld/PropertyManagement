package com.example.propertymanagementapp.data.remotedatasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.api.ApiClient
import com.example.propertymanagementapp.data.model.*
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.adapter.rxjava2.HttpException

class RemoteData {

    var disposable: Disposable?= null
    fun getRegisterResponse(registerRequest: RegisterRequest): MutableLiveData<RegisterResponse> {

        var liveData = MutableLiveData<RegisterResponse>()
        var registeredResponse = ApiClient.getUserApiService().registerUsers(registerRequest)

        registeredResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getRegisterObserver(liveData))
//        registeredResponse.enqueue(object: Callback<RegisterResponse> {
//            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                if(response.isSuccessful){
//                    liveData.value = response.body()
//                }else {
//                    val errorResponseJson = JSONObject(response.errorBody()?.string())
//                    Log.d("abc", errorResponseJson.toString())
//                    liveData.value = RegisterResponse(null, errorResponseJson.getBoolean("error"), errorResponseJson.getString("message"))
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Log.d("abc", "Unable to register user")
//                liveData.value = RegisterResponse(null, false, t.message?:"Unable to register")
//            }
//
//        })
        return liveData
    }

    fun getLoginResponse(loginRequest: LoginRequest): MutableLiveData<LoginResponse>{

        var liveData = MutableLiveData<LoginResponse>()
        var loginResponse = ApiClient.getUserApiService().loginUsers(loginRequest)

        loginResponse.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getLoginObserver(liveData))

        return liveData
    }

    fun addTenantResponse(tenantRequest: TenantRequest): MutableLiveData<TenantResponse>{

        var liveData = MutableLiveData<TenantResponse>()
        var tenantResponse = ApiClient.getUserApiService().addTenant(tenantRequest)

        tenantResponse.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(addTenantObserver(liveData))

        return liveData
    }

    private fun getRegisterObserver(liveData: MutableLiveData<RegisterResponse>):
            SingleObserver<RegisterResponse> {
        return object: SingleObserver<RegisterResponse>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onSuccess(t: RegisterResponse) {
                liveData.value = t
                Log.d("abc","Register success user: ${t.data?.name}")

                disposable?.dispose()
            }

            override fun onError(e: Throwable) {
                Log.d("abc", "Failed register")
                val errorResponse = JSONObject((e as HttpException).response()?.errorBody()?.string())
                liveData.value = RegisterResponse(null, errorResponse.getBoolean("error"), errorResponse.getString("message"))
            }

        }
    }

    private fun addTenantObserver(liveData: MutableLiveData<TenantResponse>):
            SingleObserver<TenantResponse> {
        return object: SingleObserver<TenantResponse>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onSuccess(t: TenantResponse) {
                liveData.value = t
                Log.d("abc","Add tenant success : ${t.data?.name}")

                disposable?.dispose()
            }

            override fun onError(e: Throwable) {
                Log.d("abc", "Failed register")
                val errorResponse = JSONObject((e as HttpException).response()?.errorBody()?.string())
                liveData.value = TenantResponse(null, errorResponse.getBoolean("error"))
            }

        }
    }

    private fun getLoginObserver(liveData: MutableLiveData<LoginResponse>):
            SingleObserver<LoginResponse>{
        return object: SingleObserver<LoginResponse>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onSuccess(t: LoginResponse) {
                liveData.value = t
                Log.d("abc","Login success user: ${t.user?.name}")

                disposable?.dispose()
            }

            override fun onError(e: Throwable) {
                Log.d("abc", "Failed login")
                val errorResponse = JSONObject((e as HttpException).response()?.errorBody()?.string())
                liveData.value = LoginResponse(null, null, errorResponse.getBoolean("error"), errorResponse.getString("message"))
            }

        }
    }

}