package com.example.clean_architecture.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.clean_architecture.presentation.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected fun <T> StateFlow<UIState<T>>.collectState(
        onLoading : () ->Unit,
        onError : (message :String) -> Unit,
        onSuccess : (data : T) ->Unit
    ) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                this@collectState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            onLoading()
                        }
                        is UIState.Error -> {
                            onError(state.message)
                        }
                        is UIState.Success -> {
                            onSuccess(state.data)
                        }
                        is UIState.Empty -> {}
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }
    protected open fun initialize(){}
    protected open fun setUpClickListeners(){}
    protected open fun setUpObservers(){}
    protected open fun setUpRequests(){}

}