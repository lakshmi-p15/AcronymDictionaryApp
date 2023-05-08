package com.example.acronymdictionaryapp.feature_acronym.data.repository

import com.example.acronymdictionaryapp.core.util.Resource
import com.example.acronymdictionaryapp.feature_acronym.data.remote.AcronymApi
import com.example.acronymdictionaryapp.feature_acronym.domain.model.AcronymData
import com.example.acronymdictionaryapp.feature_acronym.domain.repository.AcronymInfoRepository

/**
 *  Repository class used to get data from network api as single source of truth.
 *  This class fetched response based on sort-form provided by the user.
 *  It implements a repository interface in domain layer for ease of testing.
 */

class AcronymInfoRepositoryImpl (
    private val api: AcronymApi //network
        ): AcronymInfoRepository {
            override suspend fun getAcronymInfo(sortForm: String): Resource<AcronymData> {
                val response = api.getAcronymData(sortForm)
                return if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        Resource.Success(responseBody)
                    }else{
                        Resource.Error(response)
                    }
                }else{
                    Resource.Error(response)
                }
            }
}