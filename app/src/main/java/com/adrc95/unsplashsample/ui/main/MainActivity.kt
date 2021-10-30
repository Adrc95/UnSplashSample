package com.adrc95.unsplashsample.ui.main

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.databinding.ActivityMainBinding
import com.adrc95.unsplashsample.ui.common.EndlessRecyclerOnScrollListener
import com.adrc95.unsplashsample.ui.common.EventObserver
import com.adrc95.unsplashsample.ui.common.GridSpacingItemDecoration
import com.adrc95.unsplashsample.ui.common.extension.setVisible
import com.adrc95.unsplashsample.ui.common.view.BaseActivity
import com.adrc95.unsplashsample.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(viewModel::onPhotoClicked)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun bindView(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityCreated() {
        initializeToolbar()
        initializeList()
        initializeNavigation()
    }

    override fun initFlows() {
        launch {
            viewModel.state.collect {
                manageState(it)
            }
        }
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

    private fun initializeNavigation() {
        viewModel.navigateToPhotoDetail.observe(this, EventObserver { photo ->
            val intent = DetailActivity.getIntent(this, photo.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        })
    }

    private fun manageState(state: MainViewState) {

        if (state is MainViewState.Loading) {
            showLoading()
        }
        else {
            hideLoading()
        }

        when (state) {
            is MainViewState.ShowPhotos -> {
                renderPhotos(state.photos)
            }
            is MainViewState.LoadPhotos -> {
                viewModel.onLoadPhotos()
            }
        }
    }

    private fun showLoading() = with(binding)  {
        loading.setVisible(true)
    }

    private fun hideLoading() = with(binding)  {
        loading.setVisible(false)
    }

    private fun renderPhotos(photos: List<Photo>) {
        adapter.photos = adapter.photos + photos
    }

}