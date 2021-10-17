package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.invitaciones.MatchRequestsInfo
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface MatchesRemoteRepository {
    @POST("match/matchInvitations")
    fun matchInvitations(
        @Body info: MatchRequestsInfo
    ): retrofit2.Call<ResponseBody>
}