package com.example.acronymdictionaryapp.feature_acronym.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymdictionaryapp.core.util.Resource
import com.example.acronymdictionaryapp.feature_acronym.data.util.ValidationUtil
import com.example.acronymdictionaryapp.feature_acronym.data.remote.AcronymApi
import com.example.acronymdictionaryapp.feature_acronym.data.repository.AcronymInfoRepositoryImpl
import com.example.acronymdictionaryapp.feature_acronym.domain.model.AcronymData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/**
 * ViewModel class handles data and state for the MainActivity UI.
 * This class has logic for calling Coroutine to fetch longforms for given sortform.
 * It handles network status based on Resource and ValidationUtil class
 */

class MainViewModel : ViewModel() {

    private val _largeFormList = MutableLiveData<List<String>>()
    val largeFormList: LiveData<List<String>>
    get() = _largeFormList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val loading = MutableLiveData(View.GONE)
    val rvVisibility = MutableLiveData(View.GONE)

    private val retrofitClient by lazy { AcronymApi.invoke() }
    private val repository by lazy { AcronymInfoRepositoryImpl(retrofitClient) }

    fun getAcronymData(sortForm: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                when(val response = repository.getAcronymInfo(sortForm)) {
                    is Resource.Loading -> {
                        loading.postValue(View.VISIBLE)
                    }
                    is Resource.Success -> {
                        getLargeFormsList(response.data)
                    }
                    is Resource.Error -> {
                        onError(response.toString())
                    }
                }
            }catch (ex: UnknownHostException){
                onError(ValidationUtil.NETWORK_ERROR_MESSAGE)
            }catch (ex: java.lang.Exception){
                onError(ex.stackTraceToString())
            }
        }
    }

    private fun getLargeFormsList(acronymData: AcronymData){
        if (acronymData.isNotEmpty() && (acronymData[0].lfs.isNotEmpty())){
            val tempLfArrayList = mutableListOf<String>()
            for (lfItem in acronymData[0].lfs){
                tempLfArrayList.add(lfItem.lf)
            }
            _largeFormList.postValue(tempLfArrayList)
        } else{
            onError(ValidationUtil.RESPONSE_ERROR_MESSAGE)
        }
    }

    private fun onError(message: String){
        _errorMessage.postValue(message)
        loading.postValue(View.GONE)
    }

}