package com.transactions.homework.ui.transactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kobanister.viewbindingannotations.annotation.BindFragment
import com.transactions.homework.databinding.FragmentTransactionsBinding
import com.transactions.homework.ui.base.BaseFragment
import com.transactions.homework.ui.transactions.adapter.TransactionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@BindFragment
@AndroidEntryPoint
class TransactionsFragment : BaseFragment<TransactionsVM, FragmentTransactionsBinding>() {
    override val viewModel: TransactionsVM by viewModels()
    override val observeFlow: TransactionsVM.() -> Unit = {}

    private val rvTransactionsAdapter by lazy {
        TransactionsAdapter().apply {
            onItemClickListener = { viewModel.onTransactionItemClick(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvTransactions.adapter = rvTransactionsAdapter
        }
    }
}
