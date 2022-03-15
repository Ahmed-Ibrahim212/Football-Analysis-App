package com.example.footballdataapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.data.TeamsDataClass.Teams
import com.example.footballdataapp.network.NetworkConstants.Companion.TOKEN
import com.example.footballdataapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FootballDetailsViewModel@Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private var _userResponse = MutableLiveData<Resource<Teams>>()
    val userResponse: LiveData<Resource<Teams>> get() = _userResponse

    fun getUserResponse(teamId: Int){
        _userResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            val profileData = authRepository.getTeams(TOKEN, teamId)
            _userResponse.postValue(handleUserData(profileData))
        }
    }
    private fun handleUserData(userData: Response<Teams>): Resource<Teams> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }
}