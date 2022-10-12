package com.mvvmrecipesmap_project.map.screens.home.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mvvmrecipesmap_project.map.screens.home.viewmodel.MapViewModel
import com.mvvmrecipesmap_project.map.util.Constant.GOOGLE_MAPS_CAMERA_ZOOM
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.mvvmrecipesmap_project.domain.models.Category
import com.mvvmrecipesmap_project.map.ui.components.CategoryChip


@Composable
fun MapScreen(
    locationRequestOnClick: () -> Unit,
    mapViewModel: MapViewModel = hiltViewModel(),
) {
    val showProgress = remember { mutableStateOf(false) }
    val showInfo = remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {}
    val venuesListState = mapViewModel.venuesListState
    val selectedPlaceState = mapViewModel.selectedPlace.observeAsState()
    val currentLocation = mapViewModel.currentLocation.observeAsState()

    LaunchedEffect(Unit) {
        mapViewModel.getPlaces(mapViewModel.getDummyLocationRequest())
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            mapViewModel.getDummyAmsterdamLocation(),
            GOOGLE_MAPS_CAMERA_ZOOM
        )
    }

    if (venuesListState.venueList?.isNotEmpty() == true) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            venuesListState.venueList.forEach { place ->
                place.geocodes?.main?.let { main ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                main.latitude!!,
                                main.longitude!!
                            )
                        ),
                        title = place.name,
                        snippet = place.location?.address,
                        onClick = {
                            mapViewModel.setSelectedPlace(place)
                            false
                        }
                    )
                }
            }
        }
    }
}

