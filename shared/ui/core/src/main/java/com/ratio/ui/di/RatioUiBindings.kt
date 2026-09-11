package com.ratio.ui.di

import com.ratio.ui.time.DevicePreferences
import com.ratio.ui.time.TimeFormatter
import com.ratio.ui.time.impl.AndroidDateTimePicker
import com.ratio.ui.time.impl.AndroidDevicePreferences
import com.ratio.ui.time.impl.DateTimePicker
import com.ratio.ui.time.impl.RatioTimeFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RatioUiBindings {
    @Binds
    fun timeFormatter(impl: RatioTimeFormatter): TimeFormatter

    @Binds
    fun deviceTimePreferences(impl: AndroidDevicePreferences): DevicePreferences

    @Binds
    fun dateTimePicker(impl: AndroidDateTimePicker): DateTimePicker
}