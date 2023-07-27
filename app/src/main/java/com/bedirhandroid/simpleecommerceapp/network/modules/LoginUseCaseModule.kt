package com.bedirhandroid.simpleecommerceapp.network.modules

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.usecases.LoginUseCase
import com.bedirhandroid.simpleecommerceapp.network.usecases.LoginUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Provides
    fun provideLoginUseCase(repo: Repo): LoginUseCase {
        return LoginUseCaseImp(repo)
    }
}
