package com.test.common.util.permission.checker

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat

class AndroidPermissionChecker(
    private val context: Context
) : PermissionChecker {

    override fun hasPermissions(permissions: Array<String>): Boolean =
        permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PERMISSION_GRANTED
        }
}
