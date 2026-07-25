package com.vopo.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.MaterialTheme as M3MaterialTheme
import androidx.compose.material3.darkColorScheme as m3DarkColorScheme
import androidx.tv.material3.MaterialTheme as TvMaterialTheme
import androidx.tv.material3.darkColorScheme as tvDarkColorScheme
import com.vopo.app.ui.design.AppColors
import com.vopo.app.ui.design.AppShapes
import com.vopo.app.ui.design.LocalAppShapes
import com.vopo.app.ui.design.LocalAppSpacing
import com.vopo.app.ui.design.rememberAppTypography

private val TvDarkColorScheme = tvDarkColorScheme(
    primary = AppColors.Brand,
    onPrimary = OnPrimary,
    surface = AppColors.Surface,
    onSurface = AppColors.TextPrimary,
    surfaceVariant = AppColors.SurfaceElevated,
    onSurfaceVariant = AppColors.TextSecondary,
    background = AppColors.CanvasElevated,
    onBackground = AppColors.TextPrimary,
    error = AppColors.Live,
    onError = OnPrimary
)

private val M3DarkColorScheme = m3DarkColorScheme(
    primary = AppColors.Brand,
    onPrimary = OnPrimary,
    surface = AppColors.Surface,
    onSurface = AppColors.TextPrimary,
    surfaceVariant = AppColors.SurfaceElevated,
    onSurfaceVariant = AppColors.TextSecondary,
    background = AppColors.CanvasElevated,
    onBackground = AppColors.TextPrimary,
    error = AppColors.Live,
    onError = OnPrimary
)

@Composable
fun VopoTheme(content: @Composable () -> Unit) {
    val typography = rememberAppTypography()
    CompositionLocalProvider(
        LocalAppSpacing provides com.vopo.app.ui.design.AppSpacing(),
        LocalAppShapes provides AppShapes()
    ) {
        TvMaterialTheme(
            colorScheme = TvDarkColorScheme,
            typography = typography,
            content = {
                M3MaterialTheme(
                    colorScheme = M3DarkColorScheme,
                    content = content
                )
            }
        )
    }
}
