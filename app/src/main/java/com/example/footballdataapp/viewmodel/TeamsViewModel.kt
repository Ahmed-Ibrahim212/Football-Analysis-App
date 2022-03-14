package com.example.footballdataapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.network.NetworkConstants.Companion.TOKEN
import com.example.footballdataapp.repository.AuthRepository
import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.Teams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel(){
    private var _userResponse = MutableLiveData<Resource<Teams>>()
    val teamsResponse: LiveData<Resource<Teams>> get() = _userResponse

    fun fetchLeagueImage(leagueId:Int){
        _userResponse.value = Resource.Loading()
        viewModelScope.launch {
            val profileData = authRepository.getTeamsImage(TOKEN,leagueId)
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