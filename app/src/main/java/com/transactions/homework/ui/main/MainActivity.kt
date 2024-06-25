package com.transactions.homework.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.kobanister.viewbindingannotations.annotation.BindActivity
import com.transactions.homework.R
import com.transactions.homework.databinding.ActivityMainBinding
import com.transactions.homework.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@BindActivity
@AndroidEntryPoint
class MainActivity : BaseActivity<MainActVM, ActivityMainBinding>() {
    override val viewModel: MainActVM by viewModels ()
    override val containerId: Int = R.id.mainContainer
    override val observeFlow: MainActVM.() -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
