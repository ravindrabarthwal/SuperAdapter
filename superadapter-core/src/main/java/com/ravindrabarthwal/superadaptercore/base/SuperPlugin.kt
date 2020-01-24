package com.ravindrabarthwal.superadaptercore.base

import androidx.recyclerview.widget.RecyclerView

interface SuperPlugin {
    fun apply(superAdapter: SuperAdapter<*, *>)
}

/**
 * All plugins that are responsible for layout manager.
 * Must implement this Interface
 */
interface LayoutManagerPlugin: SuperPlugin {
    var _layoutManager: RecyclerView.LayoutManager?

    @Throws(IllegalAccessError::class)
    fun getLayoutManager(): RecyclerView.LayoutManager {
        if (_layoutManager == null) throw IllegalAccessError("LayoutManager is not created yet. Call superAdapter.apply(RecyclerView) before accessing this method.")
        return _layoutManager!!
    }
}

/**
 * All plugins that handles the click listener must implement this plugin.
 */
interface ClickListenerPlugin: SuperPlugin {

}