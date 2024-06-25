package com.transactions.homework.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import factory.BindingFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseActivity<T : BaseVM, VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    open val binding: VB
        get() = _binding ?: throw Throwable("Binding must not be null")

    abstract val viewModel: T
    protected abstract val containerId: Int
    protected abstract val observeFlow: T.() -> Unit

    private fun observeBaseFlow() = with(viewModel) {
        observeFlow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeBaseFlow()
        this._binding = BindingFactory.getBinding(this, null)
        setContentView(binding.root)
    }

    protected open fun goBack() {
        onBackPressed()
    }

    protected inline fun <T> Flow<T>.observeInLifecycle(crossinline observer: (T) -> Unit) {
        this.onEach {
            observer(it)
        }.launchIn(lifecycleScope)
    }

    protected inline fun <T> Flow<T>.observeWhenResumed(crossinline observer: (T) -> Unit) {
        lifecycleScope.launchWhenResumed {
            observeInLifecycle(observer)
        }
    }
}
