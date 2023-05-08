package com.example.acronymdictionaryapp.feature_acronym.data.remote

import com.example.acronymdictionaryapp.feature_acronym.domain.model.AcronymData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API interface provides Retrofit client to make a network call.
 * Here GET call is used to fetch longform Abbreviations based on sortform Query as input.
 */

interface AcronymApi {

    @GET("dictionary.py")
    suspend fun getAcronymData(
        @Query("sf") shortForm: String
    ): Response<AcronymData>

    companion object {
        private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

        operator fun invoke(): AcronymApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(AcronymApi::class.java)
        }
    }
}