package com.htolintino.mydebts.commons.helpers

import android.view.View
import android.widget.EditText

class MonetaryOnFocusChange(private val editText: EditText) : View.OnFocusChangeListener {

    private val emptyValue = 0.0

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            val value = ValuesFormatter.removeMonetaryMask(
                editText.text.toString()
            ).toDouble()
            if (value == emptyValue) {
                editText.setText("")
            }
        }
    }
}