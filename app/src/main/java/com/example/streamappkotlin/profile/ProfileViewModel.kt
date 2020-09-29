package com.example.streamappkotlin.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.SingleLiveEvent
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import com.example.streamappkotlin.repository.ProfileRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.File

class ProfileViewModel(private var profileRepository: ProfileRepository) : ViewModel() {
    private val TAG = "ProfileViewModel"

    private var _updateProfile: SingleLiveEvent<UpdateResponse> = SingleLiveEvent()
    var updateProfile: SingleLiveEvent<UpdateResponse> = _updateProfile

    private var _getUser: MutableLiveData<User> = MutableLiveData()
    var getUser: LiveData<User> = _getUser

    fun updateProfile(user: User) {
        profileRepository.updateProfile(user, object : Observer<UpdateResponse> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: UpdateResponse) {
                _updateProfile.postValue(t)
            }

            override fun onError(e: Throwable) {
                _updateProfile.postValue(null)
            }

        })
    }

    fun getProfile() {
        profileRepository.getProfile(object : Observer<User> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: User) {
                _getUser.postValue(t)
            }

            override fun onError(e: Throwable) {
                _getUser.postValue(null)
            }
        })
    }

    fun updateImage(file: File) {
        profileRepository.updateImage(file, object : Observer<UpdateResponse> {

            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: $d")
            }

            override fun onNext(t: UpdateResponse) {
                Log.d(TAG, "onNext: ")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: $e")
            }

        })
    }
}