package com.pedroroig.samplepermissionsapp

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * Allows requesting permissions from Activity or Fragment.
 * @param activityOrFragment Activity or Fragment only!
 */
fun doRequestPermissions(permissionToAskFor: String, requestCode: Int, activityOrFragment: LifecycleOwner) {
    when(activityOrFragment) {
        is Activity -> ActivityCompat.requestPermissions(activityOrFragment,
            arrayOf(permissionToAskFor),
            requestCode)
        is Fragment -> activityOrFragment.requestPermissions(arrayOf(permissionToAskFor), requestCode)
        else -> throw IllegalArgumentException("Provided context must be either an Activity or a Fragment")
    }
}

fun hasPermissions(permissionToAskFor: String, ctx: Context) =
    ContextCompat.checkSelfPermission(ctx, permissionToAskFor) == PackageManager.PERMISSION_GRANTED
