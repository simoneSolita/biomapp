package com.goblin.consulting.biomapp.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
) : ViewModel() {
    var splashLoadingFinished: Boolean by mutableStateOf(false)
    var permissionOK: Boolean by mutableStateOf(false)
    var permissionKO: Boolean by mutableStateOf(false)
    var goToMap: Boolean by mutableStateOf(false)
    var appName: String by mutableStateOf("BioMapp")

    fun onSplashLoadingFinished() {
        splashLoadingFinished = true
        continueToMap()
    }

    fun onPermissionGiven() {
        permissionOK = true
        continueToMap()
    }

    fun onPermissionRefused() {
        permissionKO = true
    }

    private fun continueToMap(){
        if (permissionOK && splashLoadingFinished){
            goToMap = true
        }
    }
}