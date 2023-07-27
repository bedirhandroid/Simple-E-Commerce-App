package com.bedirhandroid.simpleecommerceapp.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bedirhandroid.simpleecommerceapp.R
import com.bumptech.glide.Glide
import java.io.Serializable

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo)
        .into(this)
}

fun <T> observerNotNull(observer: (t: T) -> Unit) = Observer<T> {
    it?.let {
        observer(it)
    }
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

inline fun <reified T> Activity.navigateTo(
    bundle: Bundle? = null,
    intentFlags: Int? = null,
    finishRequired: Boolean = false
) {
    Intent(this, T::class.java).apply {
        bundle?.let { _bundle ->
            putExtras(_bundle)
        }
        intentFlags?.let { _flags ->
            flags = _flags
        }
    }.also {
        startActivity(it)
        if (finishRequired)
            finish()
    }
}