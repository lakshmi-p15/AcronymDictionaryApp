package com.example.acronymdictionaryapp.core.util

import retrofit2.Response

sealed class Resource<T> {
    data class Loading<T>( val data: T): Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: Response<T>): Resource<T>()
}