package com.adrc95.unsplashsample.ui.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.databinding.ActivityDetailBinding
import com.adrc95.unsplashsample.ui.common.extension.launchAndCollect
import com.adrc95.unsplashsample.ui.common.extension.loadUrl
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

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
    private lateinit var detailState: DetailState

    private val viewModel : DetailViewModel by viewModels()

    override fun bindView(layoutInflater: LayoutInflater): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeDetailState()
        initializeHooks()
        initFlows()
    }

    private fun initializeDetailState() {
        detailState = buildDetailState()
    }

    private fun initializeHooks() = with(binding){
        ivBack.setOnClickListener {
            detailState.onBack()
        }
    }

    private fun initFlows() {
        launchAndCollect(viewModel.state) {
            showLoading(it.loading)
            renderPhoto(it.photo)
            it.error?.let(detailState::errorSnackbar)
        }
    }

    private fun renderPhoto(photo: Photo?) = with(binding) {
        photo?.let {
            picture.loadUrl(it.url)
            content.setVisible(true)
            ivUserPhoto.loadUrl(it.author.photo)
            tvName.text = "${it.author.firstName} ${it.author.lasName}"
            tvUsername.text = it.author.username
            tvCameraName.text = it.camera?.model
            tvCameraName.setOnClickListener {
                detailState.onCameraTextClick(photo.camera)
            }
        }
    }

    private fun showLoading(visible: Boolean) = with(binding) {
        loading.setVisible(visible)
    }

}