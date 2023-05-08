package com.example.acronymdictionaryapp.feature_acronym.data.repository

import com.example.acronymdictionaryapp.core.util.Resource
import com.example.acronymdictionaryapp.feature_acronym.data.remote.AcronymApi
import com.example.acronymdictionaryapp.feature_acronym.domain.model.AcronymData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
internal class AcronymInfoRepositoryImplTest {

    private lateinit var acronymInfoRepositoryImpl : AcronymInfoRepositoryImpl

    @Mock
    lateinit var acronymApi: AcronymApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        acronymInfoRepositoryImpl = AcronymInfoRepositoryImpl(acronymApi)
    }

    @Test
    fun testGetAcronyms_data() {
        runBlocking {
            val acronymData = AcronymData()
            Mockito.`when`(acronymApi.getAcronymData("sf"))
                .thenReturn(Response.success(acronymData))

            val response = acronymInfoRepositoryImpl.getAcronymInfo("sf")
            assertEquals(Resource.Success(acronymData), response)
        }
    }

}