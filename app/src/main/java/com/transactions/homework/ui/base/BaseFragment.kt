package com.transactions.homework.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.transactions.homework.R
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
        errorFlow.observeWhenResumed(::handleError)
        navigationFlow.observeWhenResumed(::navigate)
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

    protected open fun goBack() {
        activity?.onBackPressed()
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    private fun handleError(throwable: Throwable) {
        val snackbar = Snackbar.make(requireView(), throwable.message.toString(), 5000)
        val snackTv = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackbar.setAction(R.string.ok) { snackbar.dismiss() }
        snackbar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        snackTv.maxLines = 5
        snackbar.show()
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
