package com.test.common.util.extensions

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setAdjustResize() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}