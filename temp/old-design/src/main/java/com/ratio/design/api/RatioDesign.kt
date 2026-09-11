package com.ratio.design.api

import com.ratio.base.legacy.Theme
import com.ratio.design.RatioContext
import com.ratio.design.l0_system.RatioColors
import com.ratio.design.l0_system.RatioShapes
import com.ratio.design.l0_system.RatioTypography

@Deprecated("Old design system. Use `:ratio-design` and Material3")
interface RatioDesign {
    @Deprecated("Old design system. Use `:ratio-design` and Material3")
    fun context(): RatioContext

    @Deprecated("Old design system. Use `:ratio-design` and Material3")
    fun typography(): RatioTypography

    @Deprecated("Old design system. Use `:ratio-design` and Material3")
    fun colors(theme: Theme, isDarkModeEnabled: Boolean): RatioColors

    @Deprecated("Old design system. Use `:ratio-design` and Material3")
    fun shapes(): RatioShapes
}
