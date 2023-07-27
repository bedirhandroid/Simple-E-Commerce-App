package com.bedirhandroid.simpleecommerceapp.network.usecases.users.module

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.usecases.users.usecase.UsersUseCaseImpl
import com.bedirhandroid.simpleecommerceapp.network.usecases.users.usecase.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UsersUseCaseModule {
    @Provides
    fun providesUsersList(repo: Repo): UsersUseCase {
        return UsersUseCaseImpl(repo)
    }
}