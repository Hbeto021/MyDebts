package com.htolintino.mydebts.commons.components

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.htolintino.mydebts.R
import kotlinx.android.synthetic.main.dialog_selector.view.*

class DialogSelector {

    class Builder {

        private lateinit var context: Context
        private var title: String = ""
        private var message: String = ""
        private lateinit var listItems: List<String>
        private lateinit var selectorClick: DialogSelectorClick

        fun context(context: Context) = apply { this.context = context }
        fun title(title: String) = apply { this.title = title }
        fun message(message: String) = apply { this.message = message }
        fun listItems(listItems: List<String>) = apply { this.listItems = listItems }
        fun onItemClick(selectorClick: DialogSelectorClick) = apply {
            this.selectorClick = selectorClick
        }

        fun build(): AlertDialog {
            val dialogView = View.inflate(context, R.layout.dialog_selector, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)

            val alertDialog = builder.create()

            loadViews(dialogView)
            configListeners(dialogView, alertDialog)

            return alertDialog
        }

        private fun loadViews(view: View) {
            if (title.isNotEmpty()) {
                view.dialogSelectorTitle.apply {
                    visibility = View.VISIBLE
                    text = title
                }
            }

            if (message.isNotEmpty()) {
                view.dialogSelectorMessage.apply {
                    visibility = View.VISIBLE
                    text = message
                }
            }

            view.listViewItems.apply {
                this.adapter = ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_list_item_1,
                    listItems
                )
            }

        }

        private fun configListeners(view: View, dialog: AlertDialog) {
            view.listViewItems.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    selectorClick.onItemClick(listItems[position])
                    dialog.dismiss()
                }

            view.dialogSelectorCancel.setOnClickListener {
                dialog.dismiss()
            }
        }

    }

    interface DialogSelectorClick {
        fun onItemClick(item: String)
    }

}