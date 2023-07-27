package com.bedirhandroid.simpleecommerceapp.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import com.bedirhandroid.simpleecommerceapp.R
import com.bumptech.glide.Glide
import java.io.Serializable

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo)
        .into(this)
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}

typealias ButtonAction = Pair<Int, () -> Unit>
val defaultPositiveAction = R.string.alert_msg_ok_button to {}

fun Context.showAlert(
    msg: String,
    title: String = "",
    neutral: ButtonAction? = null,
    negative: ButtonAction? = null,
    positive: ButtonAction = defaultPositiveAction,
    positiveAction: (() -> Unit)? = null,
): AlertDialog? = AlertDialog.Builder(this).run {
    fun ButtonAction.setButton(f: (Int, DialogInterface.OnClickListener) -> Unit) =
        f(first) { _, _ -> second() }

    setCancelable(false)
    if (title.isNotEmpty())
        setTitle(title)

    setMessage(msg)
    neutral?.setButton(::setNeutralButton)
    positive.copy(second = positiveAction ?: positive.second).setButton(::setPositiveButton)
    negative?.setButton(::setNegativeButton)

    create()
    show()
}