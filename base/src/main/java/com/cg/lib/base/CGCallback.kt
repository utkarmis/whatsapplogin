package com.cg.lib.base

import com.cg.lib.base.model.CGUserData

/* @CGCallback -> class that will return the result of social login
* */
interface CGCallback {
    fun onSuccess(user: CGUserData)
    fun onError(th: Throwable)
}