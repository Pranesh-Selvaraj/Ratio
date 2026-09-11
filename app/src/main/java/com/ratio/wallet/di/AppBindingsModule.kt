package com.ratio.wallet.di

import com.ratio.domain.AppStarter
import com.ratio.wallet.RatioAppStarter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingsModule {
    @Binds
    abstract fun appStarter(appStarter: RatioAppStarter): AppStarter
}
