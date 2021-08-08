package com.test.common.util.extensions

import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun AppCompatActivity.setAdjustResize() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun AppCompatActivity.setAdjustNothing() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun Fragment.requireAppCompatActivity(): AppCompatActivity =
    requireActivity() as AppCompatActivity

fun Fragment.setOnBackPressedCallback(onBackPressed: () -> Unit) {
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

fun Fragment.disableOnBackPressed() {
    setOnBackPressedCallback { }
}

fun Toolbar.setStandardBackNavigationIcon() {
    navigationIcon = DrawerArrowDrawable(context).apply {
        progress = 1f
    }
}

fun FragmentManager.setOnFragmentShowListener(tag: String, callback: (Fragment) -> Unit) {
    var listener: FragmentManager.OnBackStackChangedListener? = null
    listener = FragmentManager.OnBackStackChangedListener {
        removeOnBackStackChangedListener(checkNotNull(listener))
        val shownFragment = findFragmentByTag(tag)
        if (shownFragment != null) {
            callback(shownFragment)
        }
    }
    addOnBackStackChangedListener(listener)
}
