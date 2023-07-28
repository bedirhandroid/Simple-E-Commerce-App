package com.bedirhandroid.simpleecommerceapp.network.usecases.users.usecase

import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
    suspend fun getUsers(): Flow<List<UsersResponseUi>?>
}