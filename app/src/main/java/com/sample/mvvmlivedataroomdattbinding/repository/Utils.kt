package com.example.mvvm.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Utils {
    companion object {
        /*for checking internet connection*/
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            return isConnected
        }
    }
}
