package com.adrc95.unsplashsample.ui.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityDetailBinding
import com.adrc95.unsplashsample.ui.common.EventObserver
import com.adrc95.unsplashsample.ui.common.extension.loadUrl
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    companion object {
        const val PHOTO = "DetailActivity:photoId"

        fun getIntent(ctx: Context, id: String): Intent {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(PHOTO, id)
            return intent
        }
    }

    private val viewModel : DetailViewModel by viewModels()

    override fun bindView(layoutInflater: LayoutInflater): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeEvents()
        initializeHooks()
    }

    private fun initializeEvents() {
        viewModel.error.observe(this, EventObserver {
            displayLoadingPhotoError()
        })

        viewModel.back.observe(this, EventObserver {
            goBack()
        })

        viewModel.cameraInfo.observe(this, EventObserver {
            showCameraInfo(it)
        })
    }

    private fun initializeHooks() = with(binding){
        ivBack.setOnClickListener {
            viewModel.onBackClick()
        }
    }

    override fun initFlows() {
        launch {
            viewModel.state.collect {
                manageState(it)
            }
        }
    }

    private fun manageState(state: DetailViewState) {
        if (state is DetailViewState.Loading) {
            showLoading()
        }
        else {
            hideLoading()
        }

        when (state) {
            DetailViewState.LoadPhotoDetail -> {
                viewModel.onLoadPhotoDetail()
            }
            is DetailViewState.RenderPhoto -> {
               renderPhoto(state.photo)
            }
        }
    }

    private fun showCameraInfo(camera: Camera) {
        val dialog = CameraInfoDialog(camera)
        dialog.show(supportFragmentManager, getString(R.string.camera_info_dialog))
    }

    private fun renderPhoto(photo: Photo) = with(binding) {
        picture.loadUrl(photo.url)
        content.setVisible(true)
        ivUserPhoto.loadUrl(photo.author.photo)
        tvName.text = "${photo.author.firstName} ${photo.author.lasName}"
        tvUsername.text = photo.author.username
        tvCameraName.text = photo.camera?.model
        tvCameraName.setOnClickListener {
            viewModel.onCameraTextClick(photo.camera)
        }
    }

    private fun displayLoadingPhotoError() {
        Snackbar.make(binding.root, R.string.loading_photo_error, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun showLoading() = with(binding) {
        loading.setVisible(true)
    }

    private fun hideLoading() = with(binding) {
        loading.setVisible(false)
    }

    private fun goBack() {
        finish()
    }

}