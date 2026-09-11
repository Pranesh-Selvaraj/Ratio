package com.ratio.ui.time

import java.util.Locale

interface DevicePreferences {
    fun is24HourFormat(): Boolean
    fun locale(): Locale
}