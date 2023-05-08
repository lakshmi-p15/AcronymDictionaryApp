package com.example.acronymdictionaryapp.feature_acronym.domain.repository

import com.example.acronymdictionaryapp.core.util.Resource
import com.example.acronymdictionaryapp.feature_acronym.domain.model.AcronymData

/**
 * Repository interface for more abstraction and ease of testing
 */

interface AcronymInfoRepository {
    suspend fun getAcronymInfo(sortForm: String): Resource<AcronymData>
}