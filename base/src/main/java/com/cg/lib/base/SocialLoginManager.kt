package com.cg.lib.base

import com.cg.lib.base.model.CGUserData

interface GoogleLoginManager {
    fun loginWithGoogle(callback: CGCallback)

}

public interface FBLoginManager {
    fun loginWithFacebook(callback: CGCallback)

}
public interface WALoginManager {
    fun loginWithWhatsApp(
        authLink: String,
        clientId: String,
        clientSecret: String,
        callback: CGCallback?)

}

public interface GFLoginManager:GoogleLoginManager,FBLoginManager {
    fun returnUserData(user:CGUserData)

}
interface SocialLoginManager : GFLoginManager,WALoginManager {

}