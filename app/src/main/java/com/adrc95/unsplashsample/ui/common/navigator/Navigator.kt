package com.adrc95.unsplashsample.ui.common.navigator

import com.adrc95.domain.model.Photo

interface Navigator {
  fun openDetail(photo : Photo)
}