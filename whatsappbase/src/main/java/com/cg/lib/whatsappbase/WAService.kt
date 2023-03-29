package com.cg.lib.whatsappbase

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * WAService class use to invoke the services using retrofit
 * Created by mikram on 21-03-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
interface WAService {
    /**returns whatsapp user details
     * @param loginRequest has waId-> whatsapp token
     * @param clientId-> whatsapp client id recieved on email from OTPLess
     * @param clientSecret-> whatsapp client secret recieved on email from OTPLess
     * */
    @POST("/")
    suspend fun getUserDetails(
        @Body loginRequest: LoginRequest,
        @Header("clientId") clientId: String,
        @Header("clientSecret") clientSecret: String
    ): WAResponse
}