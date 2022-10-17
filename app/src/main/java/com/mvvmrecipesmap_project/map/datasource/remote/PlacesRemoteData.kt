package com.mvvmrecipesmap_project.map.datasource.remote

import com.mvvmrecipesmap_project.map.model.request.LocationRequestModel
import com.mvvmrecipesmap_project.map.model.response.PlacesResponse
import com.mvvmrecipesmap_project.map.network.querybuilder.VenueRecommendationsQueryBuilder
import com.mvvmrecipesmap_project.map.network.service.PlacesService
import com.mvvmrecipesmap_project.map.network.util.NetworkResult
import javax.inject.Inject


interface IPlacesRemoteData {

    /**
     * Get places from service
     * @param requestModel
     */
    suspend fun getPlaces(requestModel: LocationRequestModel): NetworkResult<PlacesResponse>

}

class PlacesRemoteData @Inject constructor(private val remoteService: PlacesService) :
    IPlacesRemoteData {

    /**
     * Get places from service
     * @param requestModel
     */
    override suspend fun getPlaces(requestModel: LocationRequestModel): NetworkResult<PlacesResponse> {
        return remoteService.getVenueRecommendationPlaces(
            VenueRecommendationsQueryBuilder()
                .setLatitudeLongitude(
                    latitude = requestModel.latitude,
                    longitude = requestModel.longitude
                ).build()
        )
    }

}