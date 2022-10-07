package com.mvvmrecipesmap_project.map.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.mvvmrecipesmap_project.R
import com.mvvmrecipesmap_project.map.LocationUtils

@Composable
fun LocationTab() {
   Surface(
       elevation = 2.dp,
       color = MaterialTheme.colors.surface,
   ){
       Box( Modifier.fillMaxSize()
       .background(Color.Blue)){
           Text(text = "Location", modifier = Modifier.width(150.dp).align(Alignment.Center),
               fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
       }

   }
   }


   /* var currentLocation by remember { mutableStateOf(LocationUtils.getDefaultLocation()) }

    val cameraPositionState = rememberCameraPositionState()

    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        LocationUtils.getPosition(currentLocation), 12f)

    var requestLocationUpdate by remember { mutableStateOf(true)}

    MyGoogleMap(
        currentLocation,
        cameraPositionState,
        onGpsIconClick = {
            requestLocationUpdate = true
        }
    )

    if(requestLocationUpdate) {
        LocationPermissionsAndSettingDialogs {
            requestLocationUpdate = false
            LocationUtils.requestLocationResultCallback(fusedLocationProviderClient) { locationResult ->

                locationResult.lastLocation?.let { location ->
                    currentLocation = location
                }

            }
        }
    }
}

@Composable
private fun MyGoogleMap(
    currentLocation: Location,
    cameraPositionState: CameraPositionState,
    onGpsIconClick: () -> Unit) {

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(zoomControlsEnabled = false)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
    ) {
        Marker(
            state = MarkerState(position = LocationUtils.getPosition(currentLocation)),
            title = "Current Position",
            snippet = cameraPositionState.position.toString(),
        )
    }

    GpsIconButton(onIconClick = onGpsIconClick)

    DebugOverlay(cameraPositionState)
}

@Composable
private fun GpsIconButton(onIconClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier.padding(bottom = 100.dp, end = 20.dp),
                    painter = painterResource(id = R.drawable.ic_gps_fixed),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun DebugOverlay(
    cameraPositionState: CameraPositionState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        val moving =
            if (cameraPositionState.isMoving) "moving" else "not moving"
        Text(
            text = "Camera is $moving",
            fontWeight = FontWeight.Bold,
            color = Color.Blue)
        Text(
            text = "Camera position is ${cameraPositionState.position} ",
            fontWeight = FontWeight.Bold,
            color = Color.Blue)
    }
}
*/