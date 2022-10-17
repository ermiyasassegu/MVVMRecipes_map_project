package com.mvvmrecipesmap_project.sensor.injection

import android.app.Application
import com.mvvmrecipesmap_project.sensor.data.LightSensor
import com.mvvmrecipesmap_project.sensor.domain.MeasurableSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SensorModule {


        @Provides

        fun provideMeasurableSensor(app: Application): MeasurableSensor = LightSensor(app)

}