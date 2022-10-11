package com.adrc95.unsplashsample.ui.detail

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.exception.HttpError
import com.adrc95.domain.exception.NetworkError
import com.adrc95.domain.exception.UnknownApiError
import com.adrc95.domain.model.Camera
import com.adrc95.unsplashsample.R
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.buildDetailState(
    activity: Activity = this,
    fragmentManager: FragmentManager = supportFragmentManager
) = DetailState(activity, fragmentManager)

class DetailState(
    private val activity: Activity,
    private val supportFragmentManager: FragmentManager
) {

    fun onCameraTextClick(camera: Camera?) {
        camera?.let {
            //val dialog = CameraInfoDialog(it)
            //dialog.show(supportFragmentManager, activity.getString(R.string.camera_info_dialog))
        }
    }

    fun errorSnackbar(error: ApiError) {
        val stringError = when (error) {
            is HttpError -> activity.getString(R.string.loading_photo_error)
            is NetworkError -> activity.getString(R.string.loading_photo_error)
            is UnknownApiError -> activity.getString(R.string.loading_photo_error)
        }
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            stringError,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun onBack() {
        activity.finish()
    }
}