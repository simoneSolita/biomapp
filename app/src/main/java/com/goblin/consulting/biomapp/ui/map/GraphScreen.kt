package com.goblin.consulting.biomapp.ui.map

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goblin.consulting.biomapp.ui.main.MainActivity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun mapScreenViewModel(): MapViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).mapViewModelFactory()

    return viewModel(factory = MapViewModel.provideFactory(factory))
}

@ExperimentalCoroutinesApi
@Composable
fun MapScreen(
) {
    MapContent()
}

@Composable
fun MapContent(
) {
    CustomGoogleMap()
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CustomGoogleMap() {
    val cameraPositionState = rememberCameraPositionState {
        CameraPosition.fromLatLngZoom(
            LatLng(
                0.0,
                0.0
            ), 10f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    )
}