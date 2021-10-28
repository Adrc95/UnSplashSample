package com.adrc95.unsplashsample.ui.detail

import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.adrc95.domain.model.Camera
import com.adrc95.unsplashsample.databinding.DialogCameraInfoBinding

class CameraInfoDialog(private val camera: Camera) : DialogFragment() {

    lateinit var binding: DialogCameraInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogCameraInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render(camera)
    }

    fun render(camera: Camera) {
        binding.tvCamera.text = camera.name
        binding.tvIso.text = "ISO ${camera.iso}"
        binding.tvFocalLength.text = "${camera.focalLength}mm f/${camera.aperture}"
        binding.tvExposureTime.text = "${camera.exposureTime}s"
    }
}