package com.htolintino.mydebts.commons.helpers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MonetaryMaskWatcher(private val editText: EditText): TextWatcher {

    override fun afterTextChanged(inputValue: Editable?) {
        editText.removeTextChangedListener(this)
        val inputValueWithoutMask = ValuesFormatter.removeMonetaryMask(inputValue.toString())
        val formattedValue =
            ValuesFormatter.formatToMonetaryValue(inputValueWithoutMask)
        editText.setText(formattedValue)
        editText.setSelection(formattedValue.length)
        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}