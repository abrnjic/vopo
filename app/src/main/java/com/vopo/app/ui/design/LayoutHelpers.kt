package com.vopo.app.ui.design

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Returns true if the device has a compact screen width (typically a mobile phone in portrait mode).
 */
val Configuration.isCompactScreen: Boolean
    get() = screenWidthDp < 600

/**
 * Helper to dynamically determine grid columns based on the current screen width.
 */
@Composable
fun getGridColumns(compact: Int = 2, medium: Int = 4, expanded: Int = 6): Int {
    val config = LocalConfiguration.current
    return when {
        config.screenWidthDp < 600 -> compact
        config.screenWidthDp < 840 -> medium
        else -> expanded
    }
}
