package com.example.footballdataapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.data.CompetitionDataClass
import com.example.footballdataapp.network.NetworkConstants.Companion.TOKEN
import com.example.footballdataapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FootballListViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel(){
    private var _userResponse = MutableLiveData<Resource<CompetitionDataClass>>()
    val userResponse: LiveData<Resource<CompetitionDataClass>> get() = _userResponse

    private var _databaseResponse = MutableLiveData<List<Competition>>()
    val databaseResponse: MutableLiveData<List<Competition>> get() = _databaseResponse

    fun getUserResponse(){
        _userResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val profileData = authRepository.getMatches(TOKEN)
            _userResponse.postValue(handleUserData(profileData))
        }
    }
    private fun handleUserData(userData: Response<CompetitionDataClass>): Resource<CompetitionDataClass> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }


    fun insertAllTeamsImage(competition: Competition)=viewModelScope.launch(Dispatchers.IO){
        authRepository.insertImage(competition)
    }

    fun getDataImage(){
         viewModelScope.launch (Dispatchers.IO) {
             val competitions = authRepository.getCompetitionsData()
             _databaseResponse.postValue(competitions)
        }
    }
}