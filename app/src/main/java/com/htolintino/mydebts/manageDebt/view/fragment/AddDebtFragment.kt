package com.htolintino.mydebts.manageDebt.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.htolintino.mydebts.R
import com.htolintino.mydebts.manageDebt.viewModel.ManageDebtViewModel
import kotlinx.android.synthetic.main.fragment_add_debt.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDebtFragment : Fragment() {

    private val manageDebtViewModel by viewModel<ManageDebtViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_debt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configListeners()
        observers()
    }

    private fun configListeners() {
        buttonAddSaveDebt.setOnClickListener {
            manageDebtViewModel.addDebt(
                description = inputAddDebtDescription.editText?.text.toString().trim(),
                value = inputAddDebtValue.editText?.text.toString().trim(),
                month = inputAddDebtMonth.editText?.text.toString().trim()
            )
        }

        buttonAddCancelDebt.setOnClickListener {
            activity?.finish()
        }
    }

    private fun observers() {
        manageDebtViewModel.getErrorMessage().observe(this, Observer {
            Toast.makeText(activity, getString(R.string.manage_debt_error_message),
                Toast.LENGTH_SHORT).show()
        })

        manageDebtViewModel.closeManageView().observe(this, Observer {
            activity?.finish()
        })
    }

}
