package com.example.jpc_address_validation_demo.data.remote

import com.example.jpc_address_validation_demo.data.model.KomootResult
import com.example.jpc_address_validation_demo.data.model.NominatimResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.jvm.java


interface KomootApiService {
    @GET("api")
    suspend fun searchAddress(
        @Query("q") query: String,
        @Query("limit") limit: Int = 1
    ): KomootResult
}

object KomootApi {
    private const val BASE_URL = "https://photon.komoot.io/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder().addInterceptor(logger).build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val service: KomootApiService by lazy { retrofit.create(KomootApiService::class.java) }
}