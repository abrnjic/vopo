package com.vopo.app.ui.theme

import com.vopo.app.ui.design.AppSpacing
import com.vopo.app.ui.design.LocalAppSpacing

typealias Spacing = AppSpacing

val LocalSpacing = LocalAppSpacing

fun defaultSpacing(): Spacing = AppSpacing()
