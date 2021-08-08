package com.test.common.util.keyboard

import android.view.View

interface SoftKeyboardController {

    fun showKeyboard()

    fun showKeyboard(targetView: View)

    fun hideKeyboard()
}
