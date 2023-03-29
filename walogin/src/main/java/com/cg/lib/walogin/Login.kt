package com.capgemini.walogin

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cg.lib.base.CGCallback
import com.cg.lib.base.WALoginManager
import com.cg.lib.base.model.CGUserData
import com.cg.lib.whatsappbase.LoginViewModel
import com.cg.lib.whatsappbase.network.RemoteDataSource
import com.otpless.views.OtplessManager

/**
 * Whatsapp login activity using OTPLess library
 * By extending this activity you can configure the whatsapp login simplyfied
 * Created by Ikram on 22-03-2023.
 * * Copyright (c) 2022 Siemens. All rights reserved.
 * */
open class Login : AppCompatActivity(), WALoginManager {
    private var socialLoginResultCallback: CGCallback? = null
    private var waID: String = ""
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Add this to your activity
        OtplessManager.getInstance().init(this)
    }

    override fun onStart() {
        super.onStart()
        registerObservers()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
    }

    /**
     * Method to get result from whatsapp login
     * @param authLink -> auth link generated on OTPless dashboard
     * @param clientId -> clientId will be received in email from OTPLess along with clientSecret
     * @param clientSecret -> clientSecret will be received in email from OTPLess along with clientId
     * @param callback -> Login result callback model having result data of login user
     * */
    override fun loginWithWhatsApp(
        authLink: String,
        clientId: String,
        clientSecret: String,
        callback: CGCallback?
    ) {
        if (socialLoginResultCallback == null)
            socialLoginResultCallback = callback
        OtplessManager.getInstance().launch(this, authLink) { response ->
            response?.let {
                waID = it.waId
                Log.v("WhatsAppID", waID)
                val loginService = RemoteDataSource(authLink).buildApi(
                    com.cg.lib.whatsappbase.WAService::class.java,
                    baseContext
                )
                viewModel.getUserDetails(loginService, it.waId, clientId, clientSecret)
            }
        }
    }

    /**
     * Method to register the observer
     * */
    private fun registerObservers() {
        viewModel.waUserLiveData.observe(this) {
            it?.let { response ->
                if (socialLoginResultCallback != null) {
                    val loginResult = CGUserData(
                        id = response.user.waNumber,
                        token = waID,
                        name = response.user.waName,
                        birthday = response.user.timestamp,
                        email = "",
                        profilePic = null,
                        address = ""
                    )
                    socialLoginResultCallback?.onSuccess(loginResult)
                }
                Log.v("USER_DATA", response.toString())
            }
        }
    }

    /**
     * Method to remove the observer
     * */
    private fun removeObservers() {
        viewModel.waUserLiveData.removeObservers(this)

    }
}