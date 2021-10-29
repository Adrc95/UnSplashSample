package com.adrc95.unsplashsample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityMainBinding
import com.adrc95.unsplashsample.ui.common.EndlessRecyclerOnScrollListener
import com.adrc95.unsplashsample.ui.common.GridSpacingItemDecoration
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter: PhotosAdapter

    @Inject
    lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializePresenter()
        initializeToolbar()
        initializeList()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializePresenter() {
        presenter.attachView(this)
        presenter.initialize(intent.extras)
    }

    private fun initializeToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
    }

    private fun initializeList() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_small)
        val layoutManager = GridLayoutManager(this, 2)
        adapter = PhotosAdapter(presenter::onPhotoClicked)
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoading(show: Boolean) = with(binding) {
        loading.setVisible(show)
    }

    override fun renderPhotos(photos: List<Photo>) {
        adapter.photos = adapter.photos + photos
    }

}