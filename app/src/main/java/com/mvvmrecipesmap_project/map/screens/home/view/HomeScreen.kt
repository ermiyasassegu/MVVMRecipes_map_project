package com.mvvmrecipesmap_project.map.screens.home.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.mvvmrecipesmap_project.map.screens.home.viewmodel.HomeViewModel
import com.mvvmrecipesmap_project.map.util.Constant.GOOGLE_MAPS_CAMERA_ZOOM


@Composable
fun HomeScreen(
    locationRequestOnClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val showProgress = remember { mutableStateOf(false) }
    val showInfo = remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {}
    val venuesListState = homeViewModel.venuesListState
    val selectedPlaceState = homeViewModel.selectedPlace.observeAsState()
    val currentLocation = homeViewModel.currentLocation.observeAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getPlaces(homeViewModel.getDummyLocationRequest())
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            homeViewModel.getDummyAmsterdamLocation(),
            GOOGLE_MAPS_CAMERA_ZOOM
        )
    }

    if(venuesListState.venueList?.isNotEmpty() == true){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){

        }
    }

}