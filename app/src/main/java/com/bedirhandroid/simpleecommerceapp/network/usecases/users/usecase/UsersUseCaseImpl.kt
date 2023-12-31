package com.bedirhandroid.simpleecommerceapp.network.usecases.users.usecase

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersUseCaseImpl @Inject constructor(private val repo: Repo) : UsersUseCase {
    override suspend fun getUsers(): Flow<List<UsersResponseUi>?> {
        return repo.getUsers().map { usersList ->
            usersList?.map { usersData ->
                UsersResponseUi(
                    address = usersData.address?.street + " " + usersData.address?.city,
                    email = usersData.email,
                    id = usersData.id,
                    firstName = usersData.name?.firstname,
                    password = usersData.password,
                    phone = usersData.phone,
                    username = usersData.username,
                    lastName = usersData.name?.lastname,
                    fullName = usersData.name?.firstname + " " + usersData.name?.lastname
                )
            }
        }
    }

}