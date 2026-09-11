package com.ratio.base.di

import com.ratio.base.resource.AndroidResourceProvider
import com.ratio.base.resource.ResourceProvider
import com.ratio.base.threading.DispatchersProvider
import com.ratio.base.threading.RatioDispatchersProvider
import com.ratio.base.time.TimeConverter
import com.ratio.base.time.TimeProvider
import com.ratio.base.time.impl.DeviceTimeProvider
import com.ratio.base.time.impl.StandardTimeConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BaseHiltBindings {
    @Binds
    fun dispatchersProvider(impl: RatioDispatchersProvider): DispatchersProvider

    @Binds
    fun bindTimezoneProvider(impl: DeviceTimeProvider): TimeProvider

    @Binds
    fun bindTimeConverter(impl: StandardTimeConverter): TimeConverter

    @Binds
    fun resourceProvider(impl: AndroidResourceProvider): ResourceProvider
}