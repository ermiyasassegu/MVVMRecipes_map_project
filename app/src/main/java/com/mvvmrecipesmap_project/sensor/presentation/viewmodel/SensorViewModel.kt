package com.mvvmrecipesmap_project.sensor.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mvvmrecipesmap_project.sensor.domain.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val sensor: MeasurableSensor,
) : ViewModel() {

    var dark: Boolean by mutableStateOf(false)
        private set

    init {
        initializeSensor()
    }

    private fun initializeSensor() {
        sensor.start()
        sensor.setOnSensorValuesChanged { values ->
            val lux = values[0]
            dark = lux < 60F
        }
    }

    override fun onCleared() {
        super.onCleared()
        sensor.stop()
    }
}