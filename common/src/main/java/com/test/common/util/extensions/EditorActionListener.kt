package com.test.common.util.extensions

import android.view.KeyEvent
import android.view.KeyEvent.ACTION_UP
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.EditorInfo.IME_ACTION_UNSPECIFIED
import android.widget.TextView

class EditorActionListener(
    private val actionType: Int,
    private val block: (TextView) -> Unit
) : TextView.OnEditorActionListener {

    override fun onEditorAction(
        view: TextView,
        actionId: Int,
        event: KeyEvent?
    ): Boolean = when (actionId) {
        IME_ACTION_UNSPECIFIED,
        actionType -> {
            if (event == null || event.action == ACTION_UP) {
                block(view)
            }
            true
        }
        else -> false
    }
}

fun TextView.setOnEditorSearchActionListener(block: (TextView) -> Unit) {
    setOnEditorActionListener(EditorActionListener(IME_ACTION_SEARCH, block))
}
