package com.bedirhandroid.simpleecommerceapp.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


//Fragment ext for navigate other fragments
fun Fragment.navigateTo(id: Int) {
    findNavController().navigate(id)
}

fun Fragment.navigateWithBundleTo(id: Int, bundle: Bundle) {
    findNavController().navigate(id, bundle)
}

fun Fragment.navigateBack() {
    findNavController().popBackStack()
}

fun Fragment.navigateBackTo(id: Int) {
    findNavController().popBackStack(id, false)
}