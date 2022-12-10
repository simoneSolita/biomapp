package com.goblin.consulting.biomapp.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.*

@Composable
fun DexterPermissions(
    onPermissionGiven: () -> Unit,
    onPermissionDenied : () -> Unit
) {
    // on below line we are creating a variable for
    // our context and activity and initializing it.
    val ctx = LocalContext.current
    getPermission(
        ctx,
        onPermissionGiven,
        onPermissionDenied
    )
}

fun getPermission(
    context: Context,
    onPermissionGiven: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    // on below line we are creating a variable
    // for dexter and initializing it.
    Dexter.withContext(context)
        .withPermissions(

            // on below line we are specifying the permissions
            // which we have to request from our user.
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).
            // on below line we are adding listener for permissions.
        withListener(object : MultiplePermissionsListener {

            // inside this on below line we are calling
            // a method on permission check to check the permissions.
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                // on below line we are checking the status of permissions.
                report.let {

                    // on below line we are checking if all the permissions are granted.
                    if (report.areAllPermissionsGranted()) {
                        // if all the permissions are granted we are displaying
                        // a simple toast message.
                        onPermissionGiven()
                    } else {

                        // if the permissions are not accepted we are displaying
                        // a toast message as permissions denied on below line.
                        onPermissionDenied()
                    }
                }
            }

            // on below line we are calling on permission
            // rational should be shown method.
            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                token: PermissionToken?
            ) {
                // in this method we are calling continue
                // permission request until permissions are not granted.
                token?.continuePermissionRequest()
            }
        }).withErrorListener {

            // on below line method will be called when dexter
            // throws any error while requesting permissions.
            onPermissionDenied()
        }.check()
}