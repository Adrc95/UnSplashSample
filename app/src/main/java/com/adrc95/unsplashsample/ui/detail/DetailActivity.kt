package com.adrc95.unsplashsample.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityDetailBinding
import com.adrc95.unsplashsample.ui.common.extension.loadUrl
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(), DetailPresenter.View {

    companion object {
        const val PHOTO = "DetailActivity:photoId"

        fun getIntent(ctx: Context, id: String): Intent {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(PHOTO, id)
            return intent
        }
    }

    private lateinit var binding : ActivityDetailBinding

    @Inject
    lateinit var presenter : DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializePresenter()
        initializeHooks()
    }

    private fun initializeBinding() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializePresenter() {
        presenter.attachView(this)
        presenter.initialize(intent.extras)
    }

    private fun initializeHooks() = with(binding){
        ivBack.setOnClickListener {
            presenter.onBackClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
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

    override fun showLoading(show: Boolean) = with(binding) {
        loading.setVisible(show)
    }

    override fun goBack() {
        finish()
    }

}