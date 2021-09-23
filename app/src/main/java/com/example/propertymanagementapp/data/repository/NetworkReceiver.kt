package com.example.propertymanagementapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkReceiver(mContext: Context) {

    val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

}