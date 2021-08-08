package com.test.common.util.permission.checker

interface PermissionChecker {

    fun hasPermissions(permissions: Array<String>): Boolean
}
