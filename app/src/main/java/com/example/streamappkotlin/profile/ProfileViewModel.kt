package com.example.streamappkotlin.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.SingleLiveEvent
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import com.example.streamappkotlin.repository.ProfileRepository

class ProfileViewModel(private var profileRepository: ProfileRepository) : ViewModel() {

    private var _updateProfile: SingleLiveEvent<UpdateResponse> = SingleLiveEvent()
    var updateProfile: SingleLiveEvent<UpdateResponse> = _updateProfile

    private var _getUser: MutableLiveData<User> = MutableLiveData()
    var getUser: LiveData<User> = _getUser

    fun updateProfile(user: User) {
        profileRepository.updateProfile(user, object : DataSourceListener<UpdateResponse> {
            override fun onResponse(response: UpdateResponse) {
                _updateProfile.postValue(response)
            }

            override fun onFailure(throwable: Throwable?) {
                _updateProfile.postValue(null)
            }

        })
    }

    fun getProfile(){
        profileRepository.getProfile(object : DataSourceListener<User>{
            override fun onResponse(response: User) {
                _getUser.postValue(response)
            }

            override fun onFailure(throwable: Throwable?) {
_getUser.postValue(null)            }

        })
    }
}