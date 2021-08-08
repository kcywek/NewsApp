package com.test.common.util.keyboard

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_FORCED
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT

class SystemSoftKeyboardController(
    private val view: View,
    private val imm: InputMethodManager
) : SoftKeyboardController {

    override fun showKeyboard() {
        imm.toggleSoftInput(SHOW_FORCED, 0)
    }

    override fun showKeyboard(targetView: View) {
        imm.showSoftInput(targetView, SHOW_IMPLICIT)
    }

    override fun hideKeyboard() {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
