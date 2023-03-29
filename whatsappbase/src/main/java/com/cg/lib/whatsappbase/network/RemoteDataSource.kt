package com.cg.lib.whatsappbase.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class will generate the Retrofit instance
 * Created by Ikram on 21-03-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
class RemoteDataSource(val baseUrl: String) {
    fun <Api> buildApi(api: Class<Api>, context: Context): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getRetrofitClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    /**
     * This method returns the retrofit client i.e. OKHttpClient instance
     * @param context -> context of the invocation
     * */
    private fun getRetrofitClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")

                }.build())
            }.also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)

            }.build()
    }
}