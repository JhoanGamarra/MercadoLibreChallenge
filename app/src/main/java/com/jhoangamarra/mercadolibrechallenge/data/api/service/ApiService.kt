package com.jhoangamarra.mercadolibrechallenge.data.api.service

import com.jhoangamarra.mercadolibrechallenge.core.AppConstants
import com.jhoangamarra.mercadolibrechallenge.data.api.model.ResponseDtoModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val LIMIT_RESULTS = 50

interface ApiService {

    @GET("/sites/{siteId}/search")
    suspend fun getProducts(
        @Path("siteId") siteId: String,
        @Query("q") query: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = LIMIT_RESULTS
    ): Response<ResponseDtoModel>

}

object RetrofitClient {

    val retrofit by lazy {
        Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}