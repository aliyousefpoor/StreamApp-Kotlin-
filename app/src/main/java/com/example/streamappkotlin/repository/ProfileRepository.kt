package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl

class ProfileRepository(
    private var userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {


}