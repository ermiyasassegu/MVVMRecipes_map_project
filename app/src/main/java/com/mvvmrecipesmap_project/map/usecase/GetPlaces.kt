package com.mvvmrecipesmap_project.map.usecase

import com.mvvmrecipesmap_project.map.model.request.LocationRequestModel
import com.mvvmrecipesmap_project.map.model.response.PlacesResponse
import com.mvvmrecipesmap_project.map.network.util.NetworkResult
import com.mvvmrecipesmap_project.map.repository.remote.PlacesRepository
import javax.inject.Inject


interface IGetPlaces {

    /**
     * Get places use case
     * @param requestModel
     */
    suspend operator fun invoke(requestModel: LocationRequestModel): NetworkResult<PlacesResponse>

}

class GetPlaces @Inject constructor(private val placesRepository: PlacesRepository) :
    IGetPlaces {

    /**
     * Get places use case
     * @param requestModel
     */
    override suspend operator fun invoke(requestModel: LocationRequestModel): NetworkResult<PlacesResponse> =
        placesRepository.getPlaces(requestModel)

}