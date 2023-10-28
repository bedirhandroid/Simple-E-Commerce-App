package com.bedirhandroid.simpleecommerceapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bedirhandroid.simpleecommerceapp.util.getBindingMethod
import com.bedirhandroid.simpleecommerceapp.util.getViewModelByLazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : HiltFragment() {

    protected lateinit var binding: VB private set
    protected lateinit var viewModel: VM private set
    private val type = javaClass.genericSuperclass as ParameterizedType
    private val bindingClass = type.actualTypeArguments[0] as Class<VB>
    private val viewModelClass = type.actualTypeArguments[1] as Class<VM>
    protected val progressBar: ProgressBar by lazy { ProgressBar.newInstance(viewModel.showProgress) }

    abstract fun initView()
    abstract fun initListeners()
    abstract fun initObservers()

    protected val observerProgressBar: Observer<Boolean> = Observer {
        if (it) {
            if (!progressBar.isProgressShown()) {
                progressBar.show(requireActivity().supportFragmentManager)
            }
            return@Observer
        }
        if (progressBar.isProgressShown()) {
            progressBar.hide()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingClass.getBindingMethod().invoke(null, inflater, container, false) as VB
        viewModel = viewModelClass.getViewModelByLazy(this)
        initView()
        initListeners()
        initObservers()
        observeBaseLiveData()
        return binding.root
    }

    //viewModel scope
    protected inline fun viewModelScope(action: VM.() -> Unit) {
        action(viewModel)
    }

    //binding scope
    protected inline fun viewBindingScope(action: VB.() -> Unit) {
        action(binding)
    }

    private fun observeBaseLiveData() {
        //observe baseViewModel LiveData
        viewModelScope {
            this.errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), getString(it.id), Toast.LENGTH_SHORT).show()
            }
            this.showProgress.observe(viewLifecycleOwner) {
                if (it) {
                    if (!progressBar.isProgressShown()) {
                        progressBar.show(requireActivity().supportFragmentManager)
                    }
                    return@observe
                }
                if (progressBar.isProgressShown()) {
                    progressBar.hide()
                }
            }
        }
    }

    //dynamic generic flow scope
    inline fun <T> Flow<T>.observeStateFlow(crossinline block: suspend T.() -> Unit) {
        lifecycleScope.launch {
            collectLatest {
                block(it)
            }
        }
    }
}