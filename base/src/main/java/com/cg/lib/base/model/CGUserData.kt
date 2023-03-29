package com.cg.lib.base.model

import android.net.Uri

data class CGUserData(
    val id: String?, val token: String = "",
    val name: String, val email: String?, val birthday: String?="",
    val profilePic: Uri?, val address: String? = ""
)