package com.pedroroig.samplepermissionsapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

const val REQUEST_CODE_LOCATION = 123

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!hasPermissions(Manifest.permission.ACCESS_FINE_LOCATION, this))
            requestLocationPermissions()
    }

    private fun requestLocationPermissions() {
        val permissionToAskFor = Manifest.permission.ACCESS_FINE_LOCATION
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                permissionToAskFor)) {
            // Show an explanation to the user
            // After the user sees the explanation, try again to request the permission.
            showSnackBar("We need to access location to provide you some services..", "Ok",
                View.OnClickListener {
                    doRequestPermissions(permissionToAskFor, REQUEST_CODE_LOCATION, this)
                }
            )

        } else {
            // No explanation needed, we can request the permission.
            doRequestPermissions(permissionToAskFor, REQUEST_CODE_LOCATION, this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permissions accepted :) doing location stuff..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permissions not accepted :(", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    @Suppress("SameParameterValue")
    private fun showSnackBar(
        mainTextString: String,
        actionText: String,
        listener: View.OnClickListener
    ): Snackbar {
        return Snackbar.make(
            findViewById(android.R.id.content),
            mainTextString,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(actionText, listener)
            .apply {
                show()
            }
    }

}
