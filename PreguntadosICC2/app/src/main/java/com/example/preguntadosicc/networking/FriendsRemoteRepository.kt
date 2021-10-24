package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.invitaciones.FriendRequestInfo
import com.example.preguntadosicc.main.invitaciones.FriendRequestsInfo
import com.example.preguntadosicc.main.models.FriendsInfo
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface FriendsRemoteRepository {
    @POST("user/friends")
    fun friendsList(
        @Body info: FriendsInfo
    ): retrofit2.Call<ResponseBody>

    @POST("user/friendRequests")
    fun friendRequests(
        @Body info: FriendRequestsInfo
    ): retrofit2.Call<ResponseBody>

    @POST("user/createRequest")
    fun createFriendRequest(
        @Body info: FriendRequestInfo
    ): retrofit2.Call<ResponseBody>

    @POST("user/acceptRequest")
    fun acceptFriendRequest(
        @Body info: FriendRequestInfo
    ): retrofit2.Call<ResponseBody>

    @POST("user/rejectRequest")
    fun rejectFriendRequest(
        @Body info: FriendRequestInfo
    ): retrofit2.Call<ResponseBody>
}