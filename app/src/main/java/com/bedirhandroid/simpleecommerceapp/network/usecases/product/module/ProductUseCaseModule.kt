package com.bedirhandroid.simpleecommerceapp.network.usecases.product.module

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.usecases.product.ProductUseCase
import com.bedirhandroid.simpleecommerceapp.network.usecases.product.ProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProductUseCaseModule {
    @Provides
    fun provideProductUseCase(repo: Repo): ProductUseCase {
        return ProductUseCaseImpl(repo)
    }
}