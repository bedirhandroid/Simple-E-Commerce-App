package com.bedirhandroid.simpleecommerceapp.network.usecases.login.module

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.usecases.login.usecase.LoginUseCase
import com.bedirhandroid.simpleecommerceapp.network.usecases.login.usecase.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Provides
    fun provideLoginUseCase(repo: Repo): LoginUseCase {
        return LoginUseCaseImpl(repo)
    }
}
