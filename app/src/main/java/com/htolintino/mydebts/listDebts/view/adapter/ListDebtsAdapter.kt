package com.htolintino.mydebts.listDebts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htolintino.mydebts.R
import com.htolintino.mydebts.commons.domain.entity.Debt
import kotlinx.android.synthetic.main.item_list_debts.view.*

class ListDebtsAdapter(private val onItemClick: (debt: Debt) -> Unit):
    RecyclerView.Adapter<ListDebtsAdapter.ListDebtsViewHolder>() {

    private val listDebts by lazy {
        mutableListOf<Debt>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDebtsViewHolder {
        return ListDebtsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_debts, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listDebts.size
    }

    override fun onBindViewHolder(holder: ListDebtsViewHolder, position: Int) {
        holder.bindView(listDebts[position])
    }

    fun updateList(listDebts: List<Debt>) {
        this.listDebts.clear()
        this.listDebts.addAll(listDebts)
        notifyDataSetChanged()
    }

    inner class ListDebtsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(debt: Debt) {
            with(itemView) {
                textViewItemListDebtsDescription.text = debt.description
                textViewItemListDebtsValue.text = debt.value
                textViewItemListDebtsMonth.text = debt.month
                setOnClickListener {
                    onItemClick.invoke(debt)
                }
            }
        }

        fun onClick() {
            this.itemView
        }

    }
}