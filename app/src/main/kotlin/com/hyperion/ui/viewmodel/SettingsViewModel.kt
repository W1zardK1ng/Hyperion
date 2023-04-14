package com.hyperion.ui.viewmodel

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.Stable
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.hyperion.domain.manager.PreferencesManager

@Stable
class SettingsViewModel(
    private val application: Application,
    val preferences: PreferencesManager
) : ViewModel() {
    fun checkForUpdates() {

    }

    fun openGitHub() {
        val intent = Intent(Intent.ACTION_VIEW, "https://github.com/zt64/Hyperion".toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        application.startActivity(intent)
    }
}