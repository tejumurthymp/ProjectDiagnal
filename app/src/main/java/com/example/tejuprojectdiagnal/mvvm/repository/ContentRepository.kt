package com.example.tejuprojectdiagnal.mvvm.repository

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ContentRepository @Inject constructor(){

    companion object {
        private const val TAG = "ContentRepository"
    }
}