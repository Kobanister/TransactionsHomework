package com.transactions.homework.ui.transactions.adapter

import android.view.ViewGroup
import com.transactions.homework.data.transaction.TransactionModel
import com.transactions.homework.databinding.ItemTransactionBinding
import com.transactions.homework.ui.base.adapter.BaseRvAdapter
import com.transactions.homework.ui.base.adapter.BaseVH
import com.transactions.homework.util.extensions.viewBinding

class TransactionsAdapter : BaseRvAdapter<TransactionModel, TransactionsAdapter.ViewHolder>() {
    var onItemClickListener: ((TransactionModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.viewBinding(ItemTransactionBinding::inflate))

    inner class ViewHolder(private val binding: ItemTransactionBinding) : BaseVH<TransactionModel>(binding.root) {
        override fun bind(item: TransactionModel) {
            // TODO: bind data
        }
    }
}
