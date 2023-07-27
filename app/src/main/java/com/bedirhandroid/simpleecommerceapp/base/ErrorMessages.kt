package com.bedirhandroid.simpleecommerceapp.base

import com.bedirhandroid.simpleecommerceapp.R

enum class ErrorMessages(val id : Int) {
    //Error message Types
    ERROR(R.string.error_message),
    UNKNOWN_ERROR(R.string.error_unknown),
    TIME_OUT(R.string.error_time_out),
    TRY_AGAIN(R.string.error_try_again),
    ERROR_EOFE(R.string.error_eofe),
}