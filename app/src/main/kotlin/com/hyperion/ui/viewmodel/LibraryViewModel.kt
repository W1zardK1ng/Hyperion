package com.hyperion.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zt.innertube.domain.repository.InnerTubeRepository

class LibraryViewModel(
    private val innerTube: InnerTubeRepository
) : ViewModel() {

}