package com.cg.lib.whatsappbase
/**
 * WhatsApp Login request model data class
 * */
data class LoginRequest(val waId:String)

/**
 * WhatsApp Login response model data class
 * */
data class WAResponse(val ok:Boolean,val user:User)

/**
 * WhatsApp User model data class
 * */
data class User(val waNumber:String,val waName:String,val timestamp:String)