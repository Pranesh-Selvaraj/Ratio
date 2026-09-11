package com.ratio.legacy.utils

fun String?.isNotNullOrBlank(): Boolean {
    return this != null && this.isNotBlank()
}
