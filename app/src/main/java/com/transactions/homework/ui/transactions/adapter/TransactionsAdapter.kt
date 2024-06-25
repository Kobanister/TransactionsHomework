package com.transactions.homework.ui.transactions.adapter

import android.view.ViewGroup
import com.transactions.homework.R
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.databinding.ItemTransactionBinding
import com.transactions.homework.ui.base.adapter.BaseRvAdapter
import com.transactions.homework.ui.base.adapter.BaseVH
import com.transactions.homework.util.extensions.viewBinding

class TransactionsAdapter : BaseRvAdapter<TransactionUIModel, TransactionsAdapter.ViewHolder>() {
    var onItemClickListener: ((TransactionUIModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.viewBinding(ItemTransactionBinding::inflate))

    inner class ViewHolder(private val binding: ItemTransactionBinding) : BaseVH<TransactionUIModel>(binding.root) {
        override fun bind(item: TransactionUIModel) {
            with(binding) {
                itemTransactionTvName.text = item.name
                itemTransactionTvDate.text = item.date
                itemTransactionTvAmount.text = ctx().getString(R.string.transactionsTvAmount, item.amount)
            }
        }
    }
}
