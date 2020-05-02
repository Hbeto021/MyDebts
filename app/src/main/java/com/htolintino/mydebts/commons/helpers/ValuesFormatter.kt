package com.htolintino.mydebts.commons.helpers

import java.text.DateFormatSymbols
import java.text.NumberFormat

class ValuesFormatter {

    companion object {

        fun formatToMonetaryValue(inputValue: String): String {
            if (inputValue.isNotEmpty()) {
                val numberFormat = NumberFormat.getCurrencyInstance()
                return numberFormat.format(inputValue.toDouble())
            }

            return ""
        }

        fun removeMonetaryMask(value: String): String {
            val replacedValue = value.replace("[^z0-9 ]".toRegex(), "").toDouble() / 100
            return replacedValue.toString()
        }

        fun formatMonth(value: Int): String {
            val dateFormat = DateFormatSymbols()
            val month = dateFormat.months[value]
            return month.replace(month[0], month[0].toUpperCase())
        }

    }
}