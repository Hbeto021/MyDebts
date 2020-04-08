package com.htolintino.mydebts.commons.router

import android.content.Context
import android.content.Intent
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.manageDebt.view.activity.ManageDebtActivity

class AppRouter {

    companion object {

        const val DEBT_PARSER_KEY = "debtParserKey"

        fun startManageDebtActivity(debt: Debt, context: Context) {
            val intent = getManageActivityIntent(context)
            intent.putExtra(DEBT_PARSER_KEY, debt)
            context.startActivity(intent)
        }

        fun startManageDebtActivity(context: Context) {
            context.startActivity(getManageActivityIntent(context))
        }


        private fun getManageActivityIntent(context: Context): Intent {
            return Intent(context, ManageDebtActivity::class.java)
        }

    }

}