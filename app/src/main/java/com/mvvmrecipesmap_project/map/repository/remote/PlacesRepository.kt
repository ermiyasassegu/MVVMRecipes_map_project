package com.mvvmrecipesmap_project.map.repository.remote

import com.mvvmrecipesmap_project.map.datasource.remote.PlacesRemoteData
import com.mvvmrecipesmap_project.map.model.request.LocationRequestModel
import com.mvvmrecipesmap_project.map.model.response.PlacesResponse
import com.mvvmrecipesmap_project.map.network.util.NetworkResult
import javax.inject.Inject


interface IPlacesRepository {

    /**
     * Get places from service
     * @param requestModel
     */
    suspend fun getPlaces(requestModel: LocationRequestModel): NetworkResult<PlacesResponse>

}


class PlacesRepository @Inject constructor(private val remote: PlacesRemoteData) :
    IPlacesRepository {

    /**
     * Get places from service
     * @param requestModel
     */
    override suspend fun getPlaces(requestModel: LocationRequestModel): NetworkResult<PlacesResponse> {
        return remote.getPlaces(requestModel)
    }

}