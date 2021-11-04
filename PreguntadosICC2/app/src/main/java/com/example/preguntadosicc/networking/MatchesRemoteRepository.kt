package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.Perfil.AnswerInfo
import com.example.preguntadosicc.main.fragments.InviteFriendInfo
import com.example.preguntadosicc.main.invitaciones.CreateMatchInfo
import com.example.preguntadosicc.main.invitaciones.FriendRequestsInfo
import com.example.preguntadosicc.main.invitaciones.MatchInvitationsInfo
import com.example.preguntadosicc.main.invitaciones.MatchRequestsInfo
import com.example.preguntadosicc.main.models.GetPlayersInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchesRemoteRepository {
    @POST("match/matchInvitations")
    fun matchInvitations(
        @Body info: MatchRequestsInfo
    ): Call<ResponseBody>

    @POST("match/acceptInvitation")
    fun matchAcceptInvitation(
        @Body info: MatchInvitationsInfo
    ): Call<ResponseBody>

    @POST("match/rejectInvitation")
    fun matchRejectInvitation(
        @Body info: MatchInvitationsInfo
    ): Call<ResponseBody>

    @POST("match/create")
    fun matchCreate(
        @Body info: CreateMatchInfo
    ): Call<ResponseBody>

    @POST("match/getCategories")
    fun getCategories(

    ): Call<ResponseBody>

    @POST("match/invitePlayer")
    fun invitePlayer(
        @Body info: InviteFriendInfo
    ): Call<ResponseBody>

    @POST("match/players")
    fun getPlayers(
        @Body info: GetPlayersInfo
    ): Call<ResponseBody>

    @GET("match/activeMatches/{email}")
    fun getActiveMatches(
        @Path("email") email: String
    ): Call<ResponseBody>

    @POST("match/activeMatches/turn/{id}/{email}")
    fun checkUserTurn(
        @Path("id") id:Int, @Path("email") email: String
    ): Call<ResponseBody>

    @POST("match/finishedMatches")
    fun finishedMatches(
        @Body info: FriendRequestsInfo
    ): Call<ResponseBody>

    @POST("match/matchStatistics")
    fun matchStatistics(
        @Body info : AnswerInfo
    ): Call<ResponseBody>

    @POST("match/matchCategory")
    fun matchCategory(
        @Body info : GetPlayersInfo
    ): Call<ResponseBody>
}
