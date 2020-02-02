package com.assignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Class to handle static method call
 */
class Util {
    companion object {
        /**
         * To check internet connection
         */
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } else {
                connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
            }
        }
    }
}