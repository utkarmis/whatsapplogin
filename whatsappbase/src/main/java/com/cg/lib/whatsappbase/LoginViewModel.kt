package com.cg.lib.whatsappbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 * ViewModel class for login activity
 * Created by mikram on 21-03-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
class LoginViewModel : ViewModel() {
    var waUserLiveData = MutableLiveData<WAResponse>()

/**
 * Method to get the user details from repository class
 * */
    fun getUserDetails(
        loginService: WAService,
        waId: String,
        clientId: String,
        clientSecret: String
    ) {
        val repository = LoginRepository(loginService)
        viewModelScope.launch {
            val response =
                async(Dispatchers.IO) { repository.getUserDetails(waId, clientId, clientSecret) }
            waUserLiveData.value = response.await()
        }
    }
}