package com.mvvmrecipesmap_project.map.screens.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import com.mvvmrecipesmap_project.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.permissions.*
import com.mvvmrecipesmap_project.map.screens.home.viewmodel.MapViewModel



@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun PlacesScreen(
    viewModel: MapViewModel,
    obBackClicked: () -> Unit
) {
    //val context = LocalContext.current
    var locationPermission = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

        PermissionRequired(
            permissionState = locationPermission,
            permissionNotGrantedContent = {
                LocationNoGranted {
                    locationPermission.launchPermissionRequest()
                }
            },
            permissionNotAvailableContent = {}
        ){
            MapScreen(locationRequestOnClick = {  })
        }
    }

@Composable
fun LocationNoGranted(
    onButtonClicked: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, Button) = createRefs()
        Text(
            text = stringResource(id = R.string.location_needed),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )

        Button(
            onClick = { onButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .constrainAs(Button) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(title.bottom)
                }
        ) {
            Text(text = stringResource(id = R.string.request_permission))
        }
    }
}


/*@Composable
fun locationRequestOnClick() {
    val context = LocalContext.current
    val permissionViewModel : PermissionViewModel = hiltViewModel()
    Timber.d("locationRequestOnClick()")
    val permissionApproved =
        context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    if (permissionApproved) {
        requestCurrentLocation()
    } else {
        permissionViewModel.locationPermissionState.value =
            LocationPermissionTypes.REQUEST_AGAIN
    }
}*/
/*
@Composable
fun Error(onRetryClick: () -> Unit) {
    IconButton(
        onClick = { onRetryClick() },
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .padding(top = 16.dp)
    ) {
        Icon(
            Icons.Default.Refresh,
            contentDescription = null,
        )
    }
}

@Composable
private fun Rationale(
    onDoNotShowRationale: () -> Unit,
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.location_permission_rationale),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = onRequestPermission) {
                Text(
                    stringResource(id = R.string.location_permission_request_permission),
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                onClick = onDoNotShowRationale
            ) {
                Text(
                    stringResource(id = R.string.location_permission_do_not_show_rationale),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun PermissionDenied(
    navigateToSettingsScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.location_permission_permission_denied),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = navigateToSettingsScreen
        ) {
            Text(stringResource(id = R.string.location_permission_open_settings))
        }
    }
}*/


