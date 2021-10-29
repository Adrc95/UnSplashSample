package com.adrc95.unsplashsample.ui.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityDetailBinding
import com.adrc95.unsplashsample.ui.common.extension.loadUrl
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailPresenter.View, DetailPresenter>(), DetailPresenter.View {

    companion object {
        const val PHOTO = "DetailActivity:photoId"

        fun getIntent(ctx: Context, id: String): Intent {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(PHOTO, id)
            return intent
        }
    }

    @Inject
    override lateinit var presenter : DetailPresenter

    override fun bindView(layoutInflater: LayoutInflater): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeHooks()
    }

    private fun initializeHooks() = with(binding){
        ivBack.setOnClickListener {
            presenter.onBackClick()
        }
    }

    override fun showCameraInfo(camera: Camera) {
        val dialog = CameraInfoDialog(camera)
        dialog.show(supportFragmentManager, getString(R.string.camera_info_dialog))
    }

    override fun renderPhoto(photo: Photo) = with(binding) {
        picture.loadUrl(photo.url)
        content.setVisible(true)
        ivUserPhoto.loadUrl(photo.author.photo)
        tvName.text = "${photo.author.firstName} ${photo.author.lasName}"
        tvUsername.text = photo.author.username
        tvCameraName.text = photo.camera?.model
        tvCameraName.setOnClickListener {
            presenter.onCameraTextClick(photo.camera)
        }
    }

    override fun displayLoadingPhotoError() {
        Snackbar.make(binding.root, R.string.loading_photo_error, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun showLoading() = with(binding) {
        loading.setVisible(true)
    }

    override fun hideLoading() = with(binding) {
        loading.setVisible(false)
    }

    override fun goBack() {
        finish()
    }

}