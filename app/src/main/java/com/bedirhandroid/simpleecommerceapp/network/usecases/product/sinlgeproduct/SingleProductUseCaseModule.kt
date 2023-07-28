package com.bedirhandroid.simpleecommerceapp.network.usecases.product.sinlgeproduct

import com.bedirhandroid.simpleecommerceapp.base.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SingleProductUseCaseModule {
    @Provides
    fun providesSingleProductUseCase(repo: Repo): SingleProductUseCase {
        return SingleProductUseCaseImpl(repo)
    }

}