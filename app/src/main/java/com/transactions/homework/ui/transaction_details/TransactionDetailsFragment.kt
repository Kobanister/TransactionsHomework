package com.transactions.homework.ui.transaction_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kobanister.viewbindingannotations.annotation.BindFragment
import com.transactions.homework.R
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.databinding.FragmentTransactionDetailsBinding
import com.transactions.homework.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@BindFragment
@AndroidEntryPoint
class TransactionDetailsFragment : BaseFragment<TransactionDetailsVM, FragmentTransactionDetailsBinding>() {
    override val viewModel: TransactionDetailsVM by viewModels()
    override val observeFlow: TransactionDetailsVM.() -> Unit = {}

    private val safeArgs by navArgs<TransactionDetailsFragmentArgs>()
    private val transactionData: TransactionUIModel
        get() = safeArgs.model

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ivBack.setOnClickListener { goBack() }
            tvName.text = transactionData.name
            tvAccountNumber.text = transactionData.accountNumber
            tvDate.text = transactionData.date
            tvAmount.text = getString(R.string.transactionsTvAmount, transactionData.amount)
            tvDescription.text = transactionData.description
        }
    }
}
