package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.invitaciones.MatchInvitationsInfo
import com.example.preguntadosicc.main.invitaciones.MatchRequestsInfo
import okhttp3.ResponseBody
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
}
