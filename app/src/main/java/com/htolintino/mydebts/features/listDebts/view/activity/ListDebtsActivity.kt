package com.htolintino.mydebts.features.listDebts.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.view.IView
import com.htolintino.mydebts.commons.components.DialogSelector
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.commons.router.AppRouter
import com.htolintino.mydebts.features.listDebts.view.adapter.ListDebtsAdapter
import com.htolintino.mydebts.features.listDebts.viewModel.ListDebtsViewModel
import kotlinx.android.synthetic.main.activity_list_debts.*
import kotlinx.android.synthetic.main.dialog_options.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListDebtsActivity : AppCompatActivity(), IView {

    private lateinit var listDebtsAdapter: ListDebtsAdapter
    private val listDebtsViewModel by viewModel<ListDebtsViewModel>()
    private lateinit var debt: Debt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_debts)

        viewName()
        configViews()
        observers()
    }

    override fun viewName() {
        supportActionBar?.title = resources.getString(R.string.list_debts_activity_name)
    }

    override fun configViews() {
        fabAddDebt.setOnClickListener {
            AppRouter.startManageDebtActivity(this)
        }

        listDebtsAdapter = ListDebtsAdapter { debt ->
            createDialogOptions(debt).show()
        }

        recyclerViewDebts.adapter = listDebtsAdapter
    }

    override fun observers() {
        listDebtsViewModel.observeDebtsValueInMonth().observe(this, Observer { value ->
            textViewDebtsValueInMonth.text = value
        })

        listDebtsViewModel.observeCurrentListDebts().observe(this, Observer { listDebts ->
            listDebtsViewModel.fetchInfoAboutCurrentMonth(listDebts)
            listDebtsAdapter.updateList(listDebts)
        })

        listDebtsViewModel.observeListDebtPerMonth().observe(this, Observer { listDebts ->
            listDebtsViewModel.fetchInfoAboutSelectedMonth(listDebts)
            listDebtsAdapter.updateList(listDebts)
        })

        listDebtsViewModel.observeSelectedMonth().observe(this, Observer { currentDate ->
            textViewDebtsDate.text = currentDate
            textViewDebtsDate.visibility = View.VISIBLE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_debts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_filter) {
            showDialogSelector()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogSelector() {
        val listOfMonths = resources.getStringArray(R.array.months_array)
        DialogSelector.Builder()
            .context(this@ListDebtsActivity)
            .title(resources.getString(R.string.dialog_selector_title))
            .listItems(listOfMonths.toList())
            .onItemClick(object : DialogSelector.DialogSelectorClick {
                override fun onItemClick(item: String) {
                    listDebtsViewModel.fetchListDebtPerMonth(item)
                }
            })
            .build().show()
    }

    private fun createDialogOptions(debt: Debt): AlertDialog {
        val dialogView = View.inflate(this, R.layout.dialog_options, null)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.buttonDialogEditDebt.setOnClickListener {
            AppRouter.startManageDebtActivity(debt, this)
            dialog.dismiss()
        }

        dialogView.buttonDialogDeleteDebt.setOnClickListener {
            listDebtsViewModel.deleteDebt(debt)
            dialog.dismiss()
        }

        dialogView.imageViewCloseDialogOption.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }

}
