package com.ratio.domain.di

import com.ratio.domain.features.Features
import com.ratio.domain.features.RatioFeatures
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RatioCoreBindingsModule {
    @Binds
    fun bindFeatures(features: RatioFeatures): Features
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeaturesEntryPoint {
    fun getFeatures(): Features
}
