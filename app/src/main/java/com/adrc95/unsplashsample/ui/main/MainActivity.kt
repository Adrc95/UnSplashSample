package com.adrc95.unsplashsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityMainBinding
import com.adrc95.unsplashsample.ui.common.EndlessRecyclerOnScrollListener
import com.adrc95.unsplashsample.ui.common.GridSpacingItemDecoration
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainPresenter.View, MainPresenter>(), MainPresenter.View {

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(presenter::onPhotoClicked)
    }

    @Inject
    override lateinit var presenter : MainPresenter

    override fun bindView(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeToolbar()
        initializeList()
    }

    private fun initializeToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
    }

    private fun initializeList() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_small)
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvPhotos.layoutManager = layoutManager
        binding.rvPhotos.addItemDecoration(GridSpacingItemDecoration(layoutManager.spanCount,
            spacingInPixels, true, 0))
        binding.rvPhotos.setHasFixedSize(true)
        binding.rvPhotos.adapter = adapter
        binding.rvPhotos.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(page: Int) {
                presenter.onLoadMore(page)
            }
        })
    }

    override fun showLoading() = with(binding)  {
        loading.setVisible(true)
    }

    override fun hideLoading() = with(binding)  {
        loading.setVisible(false)
    }

    override fun renderPhotos(photos: List<Photo>) {
        adapter.photos = adapter.photos + photos
    }

}