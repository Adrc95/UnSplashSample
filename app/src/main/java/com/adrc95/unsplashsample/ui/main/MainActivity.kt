package com.adrc95.unsplashsample.ui.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityMainBinding
import com.adrc95.unsplashsample.ui.common.EndlessRecyclerOnScrollListener
import com.adrc95.unsplashsample.ui.common.GridSpacingItemDecoration
import com.adrc95.unsplashsample.ui.common.extension.launchAndCollect
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var mainState: MainState

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(mainState::onPhotoClicked)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun bindView(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeMainState()
        initializeToolbar()
        initializeList()
        initFlows()
    }

    private fun initFlows() {
        launchAndCollect(viewModel.state) {
            showLoading(it.loading)
            renderPhotos(it.photos)
        }
    }

    private fun initializeMainState() {
        mainState = buildMainState()
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
                viewModel.onLoadMore(page)
            }
        })
    }

    private fun showLoading(visible: Boolean) = with(binding)  {
        loading.setVisible(visible)
    }

    private fun renderPhotos(photos: List<Photo>) {
        adapter.photos = adapter.photos + photos
    }

}