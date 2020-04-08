package com.htolintino.mydebts.listDebts.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.router.AppRouter
import com.htolintino.mydebts.listDebts.view.adapter.ListDebtsAdapter
import com.htolintino.mydebts.listDebts.viewModel.ListDebtsViewModel
import kotlinx.android.synthetic.main.activity_list_debts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListDebtsActivity : AppCompatActivity() {

    private lateinit var listDebtsAdapter: ListDebtsAdapter
    private val listDebtsViewModel by viewModel<ListDebtsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_debts)

        configListeners()
        configRecyclerViewListDebts()
        observers()
    }

    private fun configListeners() {
        fabAddDebt.setOnClickListener {
            AppRouter.startManageDebtActivity( this)
        }

        listDebtsAdapter = ListDebtsAdapter { debt ->
            AppRouter.startManageDebtActivity(debt, this)
        }
    }

    private fun configRecyclerViewListDebts() {
        recyclerViewDebts.adapter = listDebtsAdapter
    }

    private fun observers() {
        listDebtsViewModel.fetchListDebts().observe(this, Observer { listDebts ->
            listDebtsAdapter.updateList(listDebts)
        })
    }

}
