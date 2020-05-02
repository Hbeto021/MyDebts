package com.htolintino.mydebts.features.manageDebt.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.view.IView
import com.htolintino.mydebts.commons.helpers.MonetaryMaskWatcher
import com.htolintino.mydebts.commons.components.DialogSelector
import com.htolintino.mydebts.commons.helpers.MonetaryOnFocusChange
import com.htolintino.mydebts.features.manageDebt.viewModel.ManageDebtViewModel
import kotlinx.android.synthetic.main.fragment_insert_debt.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertDebtFragment : Fragment(),
    IView {

    private val manageDebtViewModel by viewModel<ManageDebtViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_insert_debt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewName()
        configViews()
        observers()
    }

    override fun viewName() {
        (activity as AppCompatActivity)
            .supportActionBar?.title = resources.getString(R.string.insert_debt_fragment_name)
    }

    override fun configViews() {
        inputAddDebtValue.apply {
            addTextChangedListener(
                MonetaryMaskWatcher(
                    this
                )
            )
            onFocusChangeListener =
                MonetaryOnFocusChange(this)
        }

        buttonAddSaveDebt.setOnClickListener {
            manageDebtViewModel.addDebt(
                description = inputAddDebtDescription.text.toString().trim(),
                value = inputAddDebtValue.text.toString().trim(),
                month = inputAddDebtMonth.text.toString().trim(),
                dueDate = ""
            )
        }

        buttonAddCancelDebt.setOnClickListener {
            activity?.finish()
        }

        inputAddDebtMonth.setOnClickListener {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            showDialogSelector()
        }
    }

    override fun observers() {
        manageDebtViewModel.observeErrorMessage().observe(this, Observer {
            Toast.makeText(
                activity, getString(R.string.manage_debt_error_message),
                Toast.LENGTH_SHORT
            ).show()
        })

        manageDebtViewModel.closeManageView().observe(this, Observer {
            activity?.finish()
        })
    }

    private fun showDialogSelector() {
        val listOfMonths = resources.getStringArray(R.array.months_array)
        DialogSelector.Builder()
            .context(activity!!)
            .title(resources.getString(R.string.dialog_selector_title))
            .listItems(listOfMonths.toList())
            .onItemClick(object : DialogSelector.DialogSelectorClick {
                override fun onItemClick(item: String) {
                    inputAddDebtMonth.text = item
                }
            })
            .build().show()
    }

}
