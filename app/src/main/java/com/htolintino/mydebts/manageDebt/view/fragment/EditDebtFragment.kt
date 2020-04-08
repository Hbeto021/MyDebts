package com.htolintino.mydebts.manageDebt.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.commons.router.AppRouter
import com.htolintino.mydebts.manageDebt.viewModel.ManageDebtViewModel
import kotlinx.android.synthetic.main.fragment_edit_debt.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditDebtFragment : Fragment() {

    private val manageDebtViewModel by viewModel<ManageDebtViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_debt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configListeners()
        observers()
        loadData()
    }

    private fun configListeners() {
        buttonEditSaveDebt.setOnClickListener {
            manageDebtViewModel.editDebt(
                description = inputEditDebtDescription.editText?.text.toString().trim(),
                value = inputEditDebtValue.editText?.text.toString().trim(),
                month = inputEditDebtMonth.editText?.text.toString().trim()
            )
        }

        buttonEditCancelDebt.setOnClickListener {
            activity?.finish()
        }
    }

    private fun observers() {
        manageDebtViewModel.getErrorMessage().observe(this, Observer {
            Toast.makeText(activity, getString(R.string.manage_debt_error_message),
                Toast.LENGTH_SHORT).show()
        })

        manageDebtViewModel.getDebt().observe(this , Observer { debt ->
            loadViews(debt)
        })

        manageDebtViewModel.closeManageView().observe(this, Observer {
            activity?.finish()
        })
    }

    private fun loadData() {
        arguments?.getParcelable<Debt>(AppRouter.DEBT_PARSER_KEY).let { debt ->
            manageDebtViewModel.setDebt(debt)
        }
    }

    private fun loadViews(debt: Debt) {
        inputEditDebtDescription.editText?.setText(debt.description)
        inputEditDebtValue.editText?.setText(debt.value)
        inputEditDebtMonth.editText?.setText(debt.month)
    }

}
