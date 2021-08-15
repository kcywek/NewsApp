package com.test.newsapp.feature.common.extensions

import android.view.animation.Animation

fun Animation.onAnimationEnd(onAnimationEnd: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })
}