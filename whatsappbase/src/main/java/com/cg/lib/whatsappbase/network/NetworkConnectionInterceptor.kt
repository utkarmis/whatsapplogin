package com.cg.lib.whatsappbase.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This class will check the Internet connection
 * Created by Ikram on 23-3-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
class NetworkConnectionInterceptor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request().newBuilder().build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo = connectivityManager.activeNetworkInfo
        return (netInfo != null && netInfo.isConnected)
    }
}
