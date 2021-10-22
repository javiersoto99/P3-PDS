package com.example.preguntadosicc.database.user

import com.example.preguntadosicc.database.EntityMapper
import com.example.preguntadosicc.login.models.UserResponse

class UserEntityMapper: EntityMapper<UserEntity, UserResponse> {

    override fun mapFromCached(type: UserEntity): UserResponse {
        return  UserResponse(
            type.email,
            type.username,
            type.token
        )
    }

    override fun mapToCached(type: UserResponse): UserEntity {
        return UserEntity(
            type.email,
            type.username,
            type.token
        )
    }
}