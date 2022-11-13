package com.goblin.consulting.biomapp.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.goblin.consulting.biomapp.model.PinItem
import com.goblin.consulting.biomapp.repositories.PinRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class MapViewModel @AssistedInject constructor(
    private val pinRepository: PinRepository
) : ViewModel() {
    //Assisted Factory
    @AssistedFactory
    interface Factory {
        fun create(
        ): MapViewModel
    }

    var pins = emptyList<PinItem>()

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create() as T
            }
        }
    }


    init {
        viewModelScope.launch {


        }
    }

}