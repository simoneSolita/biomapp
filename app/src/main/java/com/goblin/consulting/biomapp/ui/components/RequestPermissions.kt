package com.goblin.consulting.biomapp.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.goblin.consulting.biomapp.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    rationaleMessage: String = stringResource(id = R.string.rationale_permission)
) {
    val permissionState = rememberPermissionState(permission = permission)

    HandleRequest(
        permissionState = permissionState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale
            ) {
                permissionState.launchPermissionRequest()
            }
        },
        content = {
//            Content (
//               text = "PERMISSION GRANTED",
//               showButton = false
//            ){}
        }
    )
}

@ExperimentalPermissionsApi
@Composable
fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
        }
    }
}

@Composable
fun Content(
    showButton: Boolean = true,
    onClick: () -> Unit
) {
    if (showButton) {
        val enableLocation = remember {
            mutableStateOf(true)
        }

        if (enableLocation.value) {
            CustomDialogPermission(
                title = stringResource(id = R.string.dialog_permission_title),
                desc = stringResource(id = R.string.dialog_permission_description),
                buttonText = stringResource(id = R.string.enable_permission),
                enableLocation,
                onClick
            )
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    if (shouldShowRationale) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_permission_title)
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(onClick = {  }) {
                    Text(text = stringResource(id = R.string.enable_permission))
                }
            }
        )
    } else {
        Content(onClick = onRequestPermission)
    }
}