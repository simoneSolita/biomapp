package com.goblin.consulting.biomapp.ui.map

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goblin.consulting.biomapp.ui.main.MainActivity
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
    val viewmodel = mapScreenViewModel()
    MapContent(

    )
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MapContent(
) {


}