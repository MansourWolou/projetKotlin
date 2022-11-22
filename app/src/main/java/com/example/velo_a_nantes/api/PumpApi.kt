package com.example.velo_a_nantes.api

import com.example.velo_a_nantes.models.Pump
import com.example.velo_a_nantes.models.Station
import retrofit2.Response
import retrofit2.http.GET

interface PumpApi {
    @GET("api/pumps")
    suspend fun getPumps(): Response<List<Pump>>
}