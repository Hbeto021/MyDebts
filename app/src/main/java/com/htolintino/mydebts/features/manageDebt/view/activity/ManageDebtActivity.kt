package com.htolintino.mydebts.features.manageDebt.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.commons.router.AppRouter
import com.htolintino.mydebts.features.manageDebt.view.fragment.InsertDebtFragment
import com.htolintino.mydebts.features.manageDebt.view.fragment.EditDebtFragment

class ManageDebtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_debt)

        loadView()
    }

    private fun loadView() {
        intent.getParcelableExtra<Debt>(AppRouter.DEBT_PARSER_KEY)?.let { debt ->
            inflateFragment(getEditDebtFragment(debt))

        } ?: inflateFragment(InsertDebtFragment())

    }

    private fun inflateFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.manage_debt_layout, fragment)
            .commit()
    }

    private fun getEditDebtFragment(debt: Debt): Fragment {
        val editDebtFragment =
            EditDebtFragment()
        val bundle = Bundle()
        bundle.putParcelable(AppRouter.DEBT_PARSER_KEY, debt)
        editDebtFragment.arguments = bundle
        return editDebtFragment
    }
}
