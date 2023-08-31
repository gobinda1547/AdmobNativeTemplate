package com.gobinda.admob.nativetemplate

import android.view.View
import com.google.android.material.color.MaterialColors

object MyColorScanner {

    /**
     * This function will basically return the surface color of the input view.
     */
    fun scanSurfaceColor(view: View): Int {
        return MaterialColors.getColor(view, com.google.android.material.R.attr.colorSurface)
    }

    /**
     * This function will basically return the onSurface color of the input view.
     */
    fun scanOnSurfaceColor(view: View): Int {
        return MaterialColors.getColor(view, com.google.android.material.R.attr.colorOnSurface)
    }

    /**
     * This function will basically return the primary color of the input view.
     */
    fun scanPrimaryColor(view: View): Int {
        return MaterialColors.getColor(view, com.google.android.material.R.attr.colorPrimary)
    }

    /**
     * This function will basically return the onPrimary color of the input view.
     */
    fun scanOnPrimaryColor(view: View): Int {
        return MaterialColors.getColor(view, com.google.android.material.R.attr.colorOnPrimary)
    }
}