package com.cg.lib.whatsappbase

/**
 * This repository will handle the all network and data layer related to whatsapp login using OTPLess
 * Created by mikram on 21-03-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
class LoginRepository(private val loginService: WAService) {
    suspend fun getUserDetails(waID: String, clientId: String, clientSecret: String): WAResponse =
        loginService.getUserDetails(LoginRequest(waID), clientId, clientSecret)
}