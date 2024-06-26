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
    override val observeFlow: TransactionDetailsVM.() -> Unit = {
        transactionDetailsFlow.observeInLifecycle(::setupUi)
    }

    private val safeArgs by navArgs<TransactionDetailsFragmentArgs>()
    private val transactionId: String
        get() = safeArgs.transactionId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate(transactionId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { goBack() }
    }

    private fun setupUi(transactionData: TransactionUIModel) {
        with(binding) {
            tvName.text = transactionData.name
            tvAccountNumber.text = transactionData.accountNumber
            tvDate.text = transactionData.date
            tvAmount.text = getString(R.string.transactionsTvAmount, transactionData.amount)
            tvDescription.text = transactionData.description
        }
    }
}
