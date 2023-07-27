package com.bedirhandroid.simpleecommerceapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.simpleecommerceapp.R


private const val TAG = "ProgressBar"

//Progress Bar for base Fragment
class ProgressBar : DialogFragment() {
    lateinit var observer: MutableLiveData<Boolean>

    companion object {
        fun newInstance(observer: MutableLiveData<Boolean>): ProgressBar {
            val fragment = ProgressBar()
            val bundle = Bundle()
            fragment.observer = observer
            fragment.arguments = bundle
            return fragment
        }
    }

    private var isShown: Boolean = false

    fun isProgressShown(): Boolean = isShown

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullScreenDialog)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        return inflater.inflate(R.layout.progress, container, false)
    }

    fun show(manager: FragmentManager) {
        show(manager, TAG)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (manager.findFragmentByTag(TAG) == null) {
            manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
            this.isShown = true
        }
    }

    fun hide() {
        if (isShown) {
            this.dismissAllowingStateLoss()
            this.isShown = false
        }
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        observer.postValue(false)
    }

}