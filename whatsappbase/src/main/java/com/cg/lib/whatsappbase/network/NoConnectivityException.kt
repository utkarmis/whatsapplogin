package com.cg.lib.whatsappbase.network

import java.io.IOException

/**
 * This is the exception which will raise when network not connected
 * Created by Ikram on 23-03-2023.
 * * Copyright (c) 2023 Capgemini. All rights reserved.
 * **/
class NoConnectivityException : IOException() {

    override fun getLocalizedMessage(): String {
        return "No Internet Connection"
    }
}
