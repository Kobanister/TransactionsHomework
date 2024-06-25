package com.transactions.homework.ui.transactions

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kobanister.viewbindingannotations.annotation.BindFragment
import com.transactions.homework.R
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.databinding.FragmentTransactionsBinding
import com.transactions.homework.ui.base.BaseFragment
import com.transactions.homework.ui.transactions.adapter.TransactionsAdapter
import com.transactions.homework.ui.transactions.model.AccountBalance
import dagger.hilt.android.AndroidEntryPoint

@BindFragment
@AndroidEntryPoint
class TransactionsFragment : BaseFragment<TransactionsVM, FragmentTransactionsBinding>() {
    override val viewModel: TransactionsVM by viewModels()
    override val observeFlow: TransactionsVM.() -> Unit = {
        progressFlow.observeInLifecycle { binding.progressView.isVisible = it }
        transactionsListFlow.observeInLifecycle(::setupTransactionsList)
        accountBalanceFlow.observeInLifecycle(::setupUi)
    }

    private val rvTransactionsAdapter by lazy {
        TransactionsAdapter().apply {
            onItemClickListener = { viewModel.onTransactionItemClick(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvTransactions.adapter = rvTransactionsAdapter
            transactionsSrl.setOnRefreshListener {
                viewModel.onRefresh()
                transactionsSrl.isRefreshing = false
            }
        }
    }

    private fun setupUi(accountBalance: AccountBalance) {
        with(binding) {
            transactionTvBalance.text = getString(R.string.transactionsTvBalance, accountBalance.balance)
        }
    }

    private fun setupTransactionsList(items: List<TransactionUIModel>) {
        rvTransactionsAdapter.setItems(items)
    }
}
