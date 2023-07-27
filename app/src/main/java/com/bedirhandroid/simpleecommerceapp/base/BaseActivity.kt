package com.bedirhandroid.simpleecommerceapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bedirhandroid.simpleecommerceapp.util.getActivityViewModel
import com.bedirhandroid.simpleecommerceapp.util.getBindingMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : HiltActivity() {
    protected lateinit var binding: VB private set
    protected lateinit var viewModel: VM private set
    private val type = javaClass.genericSuperclass as ParameterizedType
    private val bindingClass = type.actualTypeArguments[0] as Class<VB>
    private val viewModelClass = type.actualTypeArguments[1] as Class<VM>
    protected val progressBar: ProgressBar by lazy { ProgressBar.newInstance(viewModel.showProgress) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGenericBinding()
        viewModel = viewModelClass.getActivityViewModel(this)
        initView()
        initListeners()
        initObservers()
    }

    abstract fun initView()
    abstract fun initListeners()
    abstract fun initObservers()

    private fun initGenericBinding() {
        val inflateMethod = bindingClass.getBindingMethod()
        val inflater = LayoutInflater.from(this)
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val isAttachToRoot = false
        binding = inflateMethod.invoke(null, inflater, rootView, isAttachToRoot) as VB
        setContentView(binding.root)
    }

    protected inline fun viewModelScope(action: VM.() -> Unit) {
        action(viewModel)
    }

    //binding scope
    protected inline fun viewBindingScope(action: VB.() -> Unit) {
        action(binding)
    }

    private fun observeBaseLiveData() {
        viewModelScope {
            this.errorLiveData.observe(this@BaseActivity) {
                Toast.makeText(this@BaseActivity, getString(it.id), Toast.LENGTH_SHORT).show()
            }
            this.showProgress.observe(this@BaseActivity) {
                if (it) {
                    if (!progressBar.isProgressShown()) {
                        progressBar.show(this@BaseActivity.supportFragmentManager)
                    }
                    return@observe
                }
                if (progressBar.isProgressShown()) {
                    progressBar.hide()
                }
            }
        }
    }

    inline fun <T> Flow<T>.observeStateFlow(crossinline block: suspend T.() -> Unit) {
        lifecycleScope.launch {
            collect {
                block(it)
            }
        }
    }

}