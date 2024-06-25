package com.transactions.homework.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import factory.BindingFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<T : BaseVM, VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected open val binding: VB
        get() = _binding ?: throw Throwable("Binding must not be null")

    abstract val viewModel: T

    protected abstract val observeFlow: T.() -> Unit

    protected open fun observeBaseFlow() = with(viewModel) {
        observeFlow()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = BindingFactory.getBinding(this, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBaseFlow()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected inline fun <T> Flow<T>.observeInLifecycle(crossinline observer: (T) -> Unit) {
        this.onEach {
            observer(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected inline fun <T> Flow<T>.observeWhenResumed(crossinline observer: (T) -> Unit) {
        this.onEach {
            lifecycleScope.launchWhenResumed { observer(it) }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected fun <T> LiveData<T>.observe(onChanged: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer(onChanged))
    }
}
