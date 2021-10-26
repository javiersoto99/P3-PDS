package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.fragments.InviteFriendInfo
import com.example.preguntadosicc.main.invitaciones.CreateMatchInfo
import com.example.preguntadosicc.main.invitaciones.MatchInvitationsInfo
import com.example.preguntadosicc.main.invitaciones.MatchRequestsInfo
import com.example.preguntadosicc.main.models.GetPlayersInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MatchesRemoteRepository {
    @POST("match/matchInvitations")
    fun matchInvitations(
        @Body info: MatchRequestsInfo
    ): retrofit2.Call<ResponseBody>

    @POST("match/acceptInvitation")
    fun matchAcceptInvitation(
        @Body info: MatchInvitationsInfo
    ): retrofit2.Call<ResponseBody>

    @POST("match/rejectInvitation")
    fun matchRejectInvitation(
        @Body info: MatchInvitationsInfo
    ): retrofit2.Call<ResponseBody>

    @POST("match/create")
    fun matchCreate(
        @Body info: CreateMatchInfo
    ): retrofit2.Call<ResponseBody>

    @POST("match/getCategories")
    fun getCategories(

    ): retrofit2.Call<ResponseBody>

    @POST("match/invitePlayer")
    fun invitePlayer(
        @Body info: InviteFriendInfo
    ): retrofit2.Call<ResponseBody>

    @POST("match/players")
    fun getPlayers(
        @Body info: GetPlayersInfo
    ): Call<ResponseBody>
}
